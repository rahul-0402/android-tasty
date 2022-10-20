package com.rahulghag.tasty.domain.usecases

import com.rahulghag.tasty.domain.models.Recipe
import com.rahulghag.tasty.domain.repositories.RecipeRepository
import com.rahulghag.tasty.utils.Resource

class FetchRecipeListUseCase(
    private val recipeRepository: RecipeRepository,
) {
    suspend operator fun invoke(): Resource<List<Recipe>> {
        return recipeRepository.fetchRecipeList()
    }
}