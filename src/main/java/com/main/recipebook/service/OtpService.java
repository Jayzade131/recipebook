package com.main.recipebook.service;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.exception.RecipeBookException;
import com.main.recipebook.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private static final int OTP_EXPIRE_TIME = 10;

    private final RedisTemplate<String, String> redisTemplate;
    private final JavaMailSender javaMailSender;
    private final UserRepo userRepo;

    @Transactional
    public void sendOtp(String email) {


        userRepo.findByEmail(email).ifPresentOrElse(user -> {
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            redisTemplate.opsForValue().set(email, String.valueOf(otp), Duration.ofMinutes(OTP_EXPIRE_TIME));


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("jaykumarzade@gmail.com");
            message.setTo(email);
            message.setSubject("OTP for RecipeBook Website");
            message.setText("Dear user,\n" + "\n" + "We received a request to reset your password. To proceed with the password reset, please use the One-Time Password (OTP) provided below:\n" + "\n" + "**OTP:** " + otp + "\n" + "\n" + "This OTP is valid for **10 minutes**. Please use it within this time frame to successfully reset your password.\n" + "\n" + "If you did not request a password reset or if you believe this request was made in error, please ignore this email.\n" + "\n" + "Thank you,\n" + "Recipe Book Support Team\n");
            javaMailSender.send(message);
        }, () -> {
            throw new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USER_NOT_EXIST.getErrorCode(), ErrorCodeEnum.USERNAME_INVALID.getErrorMessage());
        });


    }

    public boolean validateOtp(String email, String otp) {

      String otpFromRedis =  redisTemplate.opsForValue().get(email);

        if (otpFromRedis != null && otpFromRedis.equals(otp)) {
            redisTemplate.delete(email);
            return true;
        }

        return false;
    }


}
