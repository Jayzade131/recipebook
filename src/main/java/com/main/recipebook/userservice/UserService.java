package com.main.recipebook.userservice;

import com.main.recipebook.dto.UserDto;
import com.main.recipebook.dto.UserUpdateDto;

public interface UserService {

    public UserDto getUserByUsername(String username);

    public void updateUserDetails(String username, UserUpdateDto userUpdateDto);
}
