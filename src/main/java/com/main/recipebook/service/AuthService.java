package com.main.recipebook.service;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.constant.Role;
import com.main.recipebook.dto.AuthenticationtResponse;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.exception.RecipeBookException;
import com.main.recipebook.model.User;
import com.main.recipebook.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${admin.password}")
    private String adminPassword;


    private final UserRepo userRepo;


    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;



    private final OtpService otpService;


    private final AuthenticationManager authenticationManager;

    public AuthenticationtResponse register(UserDto request) {
        if(checkUserExist(request.getUsername()))
        {
                throw new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USER_EXIST_ALREADY.getErrorCode(), ErrorCodeEnum.USER_EXIST_ALREADY.getErrorMessage());
        }
        User user = User.builder().name(request.getName()).username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail()).role(Role.USER).build();

        user = userRepo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthenticationtResponse(token, user);

    }
    public boolean checkUserExist(String username)
    {
       return userRepo.findByUsername(username).isPresent();

    }

    public AuthenticationtResponse authenticate(User request) {


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationtResponse(token, user);
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepo.findByRole(Role.ADMIN);
        if (null == adminAccount) {
            User user = User.builder().name("admin").username("admin01").password(passwordEncoder.encode(adminPassword))
                    .email("adminaccount@gmail.com").role(Role.ADMIN).build();
            userRepo.save(user);

        }

    }

    public void resetPassword(String email, String otp, String newPassword) {

        if(otpService.validateOtp(email,otp))
        {


            User user = userRepo.findByEmail(email).orElseThrow(()-> new RecipeBookException(HttpStatus.BAD_REQUEST,ErrorCodeEnum.EMAIL_NOT_FOUND.getErrorCode(),ErrorCodeEnum.EMAIL_NOT_FOUND.getErrorMessage()));
            if(this.passwordEncoder.matches(newPassword,user.getPassword()))
            {
                throw new RecipeBookException(HttpStatus.BAD_REQUEST,ErrorCodeEnum.PASSWORD_SAME.getErrorCode(),ErrorCodeEnum.PASSWORD_SAME.getErrorMessage());
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
        }
        else
        {
            throw new RecipeBookException(HttpStatus.BAD_REQUEST,ErrorCodeEnum.OTP_INVALID.getErrorCode(),ErrorCodeEnum.OTP_INVALID.getErrorMessage());
        }
    }
}
