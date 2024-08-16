package com.main.recipebook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 5, max = 15, message = "name must be between 5 and 30 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email should be valid")
    private String email;
}
