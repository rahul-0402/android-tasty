package com.rahulghag.tasty.ui.features.recipe.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulghag.tasty.domain.usecases.FetchRecipeListUseCase
import com.rahulghag.tasty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreRecipesViewModel @Inject constructor(
    private val fetchRecipeListUseCase: FetchRecipeListUseCase
) : ViewModel() {
    var uiState by mutableStateOf(RecipeListUiState())
        private set

    init {
        fetchRecipeList()
    }

    fun fetchRecipeList() {
        viewModelScope.launch {
            uiState = RecipeListUiState()
            uiState = when (val result = fetchRecipeListUseCase.invoke()) {
                is Resource.Success -> {
                    uiState.copy(isLoading = false, recipeList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    uiState.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}