package com.main.recipebook.userservice.impl;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.dto.RecipeDto;
import com.main.recipebook.exception.RecipeBookException;
import com.main.recipebook.model.Recipe;
import com.main.recipebook.repository.RecipeRepo;
import com.main.recipebook.repository.UserRepo;
import com.main.recipebook.userservice.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepo recipeRepo;

    private final UserRepo userRepo;

    public void addRecipe(RecipeDto recipeDto) {
        userRepo.findById(recipeDto.getUserId()).ifPresentOrElse(user -> {
            log.info("User Found -->" + user + " with id : " + recipeDto.getUserId());
            recipeRepo.save(Recipe.builder().title(recipeDto.getTitle()).description(recipeDto.getDescription()).ingredient(recipeDto.getIngredient()).instructions(recipeDto.getInstructions()).user(user).build());

        }, () -> {
            throw new RecipeBookException(HttpStatus.BAD_REQUEST, ErrorCodeEnum.USER_NOT_FOUND_BY_ID.getErrorCode(), ErrorCodeEnum.USER_NOT_FOUND_BY_ID.getErrorMessage());
        });


    }
}
