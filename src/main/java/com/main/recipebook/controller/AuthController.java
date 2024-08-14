package com.main.recipebook.controller;

import com.main.recipebook.constant.UrlConstants;
import com.main.recipebook.dto.AuthenticationtResponse;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.model.User;
import com.main.recipebook.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(UrlConstants.REGISTER)
    public ResponseEntity<AuthenticationtResponse> register(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping(UrlConstants.LOGIN)
    public ResponseEntity<AuthenticationtResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.authenticate(user));
    }
}
