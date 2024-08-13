package com.main.recipebook.service;

import com.main.recipebook.constant.ErrorCodeEuum;
import com.main.recipebook.dto.AuthenticationtResponse;
import com.main.recipebook.constant.Role;
import com.main.recipebook.exception.RecipeBookException;
import com.main.recipebook.model.User;
import com.main.recipebook.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationtResponse register(User request) {
        if(checkUserExist(request.getUsername()))
        {
                throw new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEuum.USER_EXIST_ALREADY.getErrorCode(), ErrorCodeEuum.USER_EXIST_ALREADY.getErrorMessage());
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
       Optional<User> user= userRepo.findByUsername(username);
       if(user.isPresent())
       {
           return true;
       }
       return false;
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
            User user = User.builder().name("admin").username("admin01").password(passwordEncoder.encode("Admin@123"))
                    .email("adminaccount@gmail.com").role(Role.ADMIN).build();
            userRepo.save(user);

        }

    }
}
