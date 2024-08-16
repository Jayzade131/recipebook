package com.main.recipebook.controller;

import com.main.recipebook.constant.UrlConstants;
import com.main.recipebook.dto.AuthenticationtResponse;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.dto.ValidationOtpDto;
import com.main.recipebook.model.User;
import com.main.recipebook.service.AuthService;
import com.main.recipebook.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;


    @PostMapping(UrlConstants.REGISTER)
    public ResponseEntity<AuthenticationtResponse> register(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping(UrlConstants.LOGIN)
    public ResponseEntity<AuthenticationtResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.authenticate(user));
    }

    @PostMapping(UrlConstants.OTP)
    public ResponseEntity<?> sendOtp(@PathVariable  String email) {
        otpService.sendOtp(email);
        return new ResponseEntity<>("OTP Sent Successfully", HttpStatus.OK);
    }

    @PostMapping(UrlConstants.VALIDATE_OTP)
    public ResponseEntity<?> validateOtp(@RequestBody ValidationOtpDto validationOtpDto) {
        authService.resetPassword(validationOtpDto.getEmail(), validationOtpDto.getOtp(), validationOtpDto.getNewPassword());
        return new ResponseEntity<>("Password Reset Successfully", HttpStatus.OK);
    }

}
