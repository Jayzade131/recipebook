package com.main.recipebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {

    private String title;

    private String description;

    private String ingredient;

    private String instructions;

    private Long userId;

}
