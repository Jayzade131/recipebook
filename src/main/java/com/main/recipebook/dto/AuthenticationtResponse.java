package com.main.recipebook.dto;

import com.main.recipebook.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationtResponse {

    private String token;

    private User user;
}
