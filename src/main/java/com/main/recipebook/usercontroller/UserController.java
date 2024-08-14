package com.main.recipebook.usercontroller;

import com.main.recipebook.constant.UrlConstants;
import com.main.recipebook.dto.UserDto;
import com.main.recipebook.dto.UserUpdateDto;
import com.main.recipebook.userservice.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(UrlConstants.GET_BY_USERNAME)
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username)
    {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping(UrlConstants.UPDATE_USER)
    public ResponseEntity<?> UpdateUser(@Valid @RequestBody UserUpdateDto userUpdateDto, @PathVariable String username)
    {
        userService.updateUserDetails(username,userUpdateDto);
        return ResponseEntity.ok("User Update Successfully");
    }




}
