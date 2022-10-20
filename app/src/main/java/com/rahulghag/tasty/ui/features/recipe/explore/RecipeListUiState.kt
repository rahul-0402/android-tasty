package com.rahulghag.tasty.ui.features.recipe.explore

import com.rahulghag.tasty.domain.models.Recipe
import com.rahulghag.tasty.utils.UiMessage

data class RecipeListUiState(
    val isLoading: Boolean = true,
    val error: UiMessage? = null,
    val recipeList: List<Recipe> = emptyList(),
)