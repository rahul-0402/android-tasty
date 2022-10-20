package com.rahulghag.tasty.domain.usecases

import com.rahulghag.tasty.domain.models.RecipeDetails
import com.rahulghag.tasty.domain.repositories.RecipeRepository
import com.rahulghag.tasty.utils.Resource

class FetchRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository,
) {
    suspend operator fun invoke(id: Int): Resource<RecipeDetails> {
        return recipeRepository.fetchRecipeDetailsById(id = id)
    }
}