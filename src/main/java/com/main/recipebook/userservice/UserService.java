package com.main.recipebook.userservice;

import com.main.recipebook.dto.ChangePasswordDto;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.dto.UserUpdateDto;

public interface UserService {

    public UserDto getUserByUsername(String username);

    public void updateUserDetails(String username, UserUpdateDto userUpdateDto);

    public void changepassword(ChangePasswordDto changePasswordDto);
}
