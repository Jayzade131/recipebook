package com.main.recipebook.usercontroller;

import com.main.recipebook.constant.UrlConstants;
import com.main.recipebook.dto.RecipeDto;
import com.main.recipebook.userservice.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(UrlConstants.ADD_RECIPE)
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.addRecipe(recipeDto);
        return ResponseEntity.ok("Recipe Added Successfully");
    }
}
