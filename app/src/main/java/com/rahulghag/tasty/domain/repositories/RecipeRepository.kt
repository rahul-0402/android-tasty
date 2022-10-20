package com.rahulghag.tasty.domain.repositories

import com.rahulghag.tasty.domain.models.Recipe
import com.rahulghag.tasty.domain.models.RecipeDetails
import com.rahulghag.tasty.utils.Resource

interface RecipeRepository {
    suspend fun fetchRecipeList(): Resource<List<Recipe>>

    suspend fun fetchRecipeDetailsById(id: Int): Resource<RecipeDetails>
}