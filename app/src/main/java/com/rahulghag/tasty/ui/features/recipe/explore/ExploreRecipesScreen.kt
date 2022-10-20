package com.rahulghag.tasty.ui.features.recipe.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rahulghag.tasty.R
import com.rahulghag.tasty.domain.models.Recipe
import com.rahulghag.tasty.ui.components.ErrorScreen
import com.rahulghag.tasty.ui.components.SearchBar

@Composable
fun ExploreRecipesScreen(
    exploreRecipesViewModel: ExploreRecipesViewModel = hiltViewModel(),
    onNavigateToDetailScreen: (id: Int) -> Unit,
) {
    val uiState = exploreRecipesViewModel.uiState

    Scaffold(
        topBar = {
            SearchBar(
                childModifier = Modifier
                    .placeholder(
                        visible = uiState.isLoading,
                        highlight = PlaceholderHighlight.fade()
                    ),
                hintText = stringResource(id = R.string.hint_search_for_recipes),
                onSearchBarClick = {}
            )
        }
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(uiState.isLoading),
            onRefresh = {
                exploreRecipesViewModel.fetchRecipeList()
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (uiState.isLoading.not()) {
                        items(uiState.recipeList) { recipe ->
                            RecipeItem(
                                recipe = recipe,
                                onRecipeItemClick = {
                                    onNavigateToDetailScreen(recipe.id)
                                }
                            )
                        }
                    } else {
                        items(10) {
                            RecipeItem(
                                childModifier = Modifier
                                    .placeholder(
                                        visible = uiState.isLoading,
                                        highlight = PlaceholderHighlight.fade()
                                    ),
                                recipe = Recipe(id = -1, imageUrl = "", name = ""),
                                onRecipeItemClick = {}
                            )
                        }
                    }
                }
            }
        }
        uiState.error?.let {
            ErrorScreen(
                errorMessage = uiState.error,
                onTryAgain = {
                    exploreRecipesViewModel.fetchRecipeList()
                }
            )
        }
    }
}

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    recipe: Recipe,
    onRecipeItemClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable {
                onRecipeItemClick()
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = childModifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = recipe.name,
            modifier = childModifier
                .fillMaxWidth()
                .padding(all = 4.dp),
            fontSize = 14.sp,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}