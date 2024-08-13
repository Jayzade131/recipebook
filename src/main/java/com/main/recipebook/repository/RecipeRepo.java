package com.main.recipebook.repository;

import com.main.recipebook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe,Long> {
}
