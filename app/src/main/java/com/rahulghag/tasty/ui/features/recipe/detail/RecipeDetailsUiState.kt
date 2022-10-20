package com.rahulghag.tasty.ui.features.recipe.detail

import com.rahulghag.tasty.domain.models.RecipeDetails
import com.rahulghag.tasty.utils.UiMessage

data class RecipeDetailsUiState(
    val isLoading: Boolean = true,
    val error: UiMessage? = null,
    val recipeDetails: RecipeDetails? = null
)