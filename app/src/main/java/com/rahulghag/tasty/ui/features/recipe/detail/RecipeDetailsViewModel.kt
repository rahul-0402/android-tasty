package com.rahulghag.tasty.ui.features.recipe.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulghag.tasty.domain.usecases.FetchRecipeDetailsUseCase
import com.rahulghag.tasty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchRecipeDetailsUseCase: FetchRecipeDetailsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(RecipeDetailsUiState())
        private set

    private val id: Int = checkNotNull(savedStateHandle["id"])

    init {
        fetchRecipeDetailsById()
    }

    fun fetchRecipeDetailsById() {
        viewModelScope.launch {
            uiState = RecipeDetailsUiState()
            uiState = when (val result = fetchRecipeDetailsUseCase.invoke(id = id)) {
                is Resource.Success -> {
                    uiState.copy(isLoading = false, recipeDetails = result.data)
                }
                is Resource.Error -> {
                    uiState.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}