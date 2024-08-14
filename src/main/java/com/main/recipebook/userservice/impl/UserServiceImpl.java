package com.main.recipebook.userservice.impl;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.dto.UserUpdateDto;
import com.main.recipebook.exception.RecipeBookException;
import com.main.recipebook.model.User;
import com.main.recipebook.repository.UserRepo;
import com.main.recipebook.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserDto getUserByUsername(String username)
    {
        User user= userRepo.findByUsername(username).orElseThrow(() -> new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USER_NOT_FOUND.getErrorCode(), ErrorCodeEnum.USER_NOT_FOUND.getErrorMessage()));

        return UserDto.builder().name(user.getName()).username(user.getUsername()).password(user.getPassword()).email(user.getEmail()).id(user.getId()).build();

    }

    public void updateUserDetails(String username, UserUpdateDto userUpdateDto)
    {
        User user= userRepo.findByUsername(username).orElseThrow(() -> new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USER_NOT_FOUND.getErrorCode(), ErrorCodeEnum.USER_NOT_FOUND.getErrorMessage()));
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        userRepo.save(user);
    }



}
