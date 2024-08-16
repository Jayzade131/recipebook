package com.main.recipebook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private String username;

    @NotBlank(message = "Password.required")
    @Size(min = 8, message = "{password.length.required}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "Password should be in 8 words including Numeric,alphabets and special character")
    private String oddPassword;

    @NotBlank(message = "Password.required")
    @Size(min = 8, message = "{password.length.required}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "Password should be in 8 words including Numeric,alphabets and special character")
    private String newPassword;
}
