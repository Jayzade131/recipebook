package com.main.recipebook.controller;

import com.main.recipebook.constant.UrlConstants;
import com.main.recipebook.dto.AuthenticationtResponse;
import com.main.recipebook.model.User;
import com.main.recipebook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(UrlConstants.REGISTER)
    public ResponseEntity<AuthenticationtResponse> register(@RequestBody User user)
    {
            return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping(UrlConstants.LOGIN)
    public ResponseEntity<AuthenticationtResponse> login(@RequestBody User user)
    {
        return ResponseEntity.ok(authService.authenticate(user));
    }
}
