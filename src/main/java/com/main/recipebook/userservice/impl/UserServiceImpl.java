package com.main.recipebook.userservice.impl;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.dto.ChangePasswordDto;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.dto.UserUpdateDto;
import com.main.recipebook.exception.RecipeBookException;
import com.main.recipebook.model.User;
import com.main.recipebook.repository.UserRepo;
import com.main.recipebook.userservice.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Cacheable(value = "user", key = "#username")
    public UserDto getUserByUsername(String username)
    {
        User user= userRepo.findByUsername(username).orElseThrow(() -> new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USERNAME_INVALID.getErrorCode(), ErrorCodeEnum.USERNAME_INVALID.getErrorMessage()+" "+username));
        log.info("User Found -->"+user +" with username : "+username);
        return UserDto.builder().name(user.getName()).username(user.getUsername()).password(user.getPassword()).email(user.getEmail()).id(user.getId()).build();

    }

    @CachePut(cacheNames = "user", key = "#username")
    public void updateUserDetails(String username, UserUpdateDto userUpdateDto)
    {
        User user= userRepo.findByUsername(username).orElseThrow(() -> new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USERNAME_INVALID.getErrorCode(), ErrorCodeEnum.USERNAME_INVALID.getErrorMessage()));
        log.info("User Found -->"+user +" with username : "+username);
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        userRepo.save(user);
    }

    public void changepassword(ChangePasswordDto changePasswordDto)
    {
        User user= userRepo.findByUsername(changePasswordDto.getUsername()).orElseThrow(() -> new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USERNAME_INVALID.getErrorCode(), ErrorCodeEnum.USERNAME_INVALID.getErrorMessage()));
        log.info("User Found -->"+user +" with username : "+changePasswordDto.getUsername());
       if(this.passwordEncoder.matches(changePasswordDto.getOddPassword(),user.getPassword()))
       {
           user.setPassword(this.passwordEncoder.encode(changePasswordDto.getNewPassword()));
           userRepo.save(user);
       }
       else
       {
           throw new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.OLD_PASSWORD_INVALID.getErrorCode(), ErrorCodeEnum.OLD_PASSWORD_INVALID.getErrorMessage());
       }
    }





}
