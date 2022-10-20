package com.rahulghag.tasty.ui.features.recipe.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rahulghag.tasty.R
import com.rahulghag.tasty.domain.models.Ingredient
import com.rahulghag.tasty.domain.models.Nutrient
import com.rahulghag.tasty.ui.components.ErrorScreen
import com.rahulghag.tasty.ui.components.TopBar
import com.rahulghag.tasty.ui.theme.preparationItemBackgroundColor
import com.rahulghag.tasty.ui.theme.preparationSectionBackgroundColor
import com.rahulghag.tasty.ui.theme.topAppBarContentColor

@Composable
fun RecipeDetailsScreen(
    recipeDetailsViewModel: RecipeDetailsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val uiState = recipeDetailsViewModel.uiState

    Scaffold(
        topBar = {
            TopBar(
                title = {},
                navigationIcon = Icons.Rounded.ArrowBack,
                onNavigationIconClick = {
                    onBackPressed()
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.FavoriteBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colors.topAppBarContentColor
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(uiState.isLoading),
            onRefresh = { recipeDetailsViewModel.fetchRecipeDetailsById() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                if (uiState.isLoading.not()) {
                    uiState.recipeDetails?.apply {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            presentedBy?.let {
                                Text(
                                    text = buildAnnotatedString {
                                        append(stringResource(id = R.string.label_presented_by))
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append(presentedBy)
                                        }
                                    }
                                )
                            }
                            featuredIn?.let {
                                Text(
                                    text = buildAnnotatedString {
                                        append(stringResource(id = R.string.label_featured_in))
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append(featuredIn)
                                        }
                                    }
                                )
                            }
                            rating?.let {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append(rating)
                                        }
                                        append(stringResource(id = R.string.label_would_make_this_again))
                                    }
                                )
                            }
                        }

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .aspectRatio(1f)
                        )

                        NutritionalInfo(nutrients = nutrients)

                        Ingredients(ingredients = ingredients)

                        Preparation(preparationSteps = preparationSteps)
                    }
                }
            }
            uiState.error?.let {
                ErrorScreen(
                    errorMessage = uiState.error,
                    onTryAgain = {
                        recipeDetailsViewModel.fetchRecipeDetailsById()
                    }
                )
            }
        }
    }
}

@Composable
fun NutritionalInfo(
    nutrients: List<Nutrient>,
    isExpanded: Boolean = false
) {
    var expandedState by remember { mutableStateOf(isExpanded) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.label_nutritional_info),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = stringResource(id = if (expandedState) R.string.label_hide_info else R.string.label_view_info),
                modifier = Modifier
                    .clickable {
                        expandedState = !expandedState
                    },
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        if (expandedState) {
            Spacer(modifier = Modifier.height(16.dp))
            nutrients.forEachIndexed { index, nutrient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nutrient.nutrientName
                    )
                    Text(
                        text = nutrient.amount,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Ingredients(
    ingredients: List<Ingredient>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.label_ingredients),
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        ingredients.forEach { ingredient ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ingredient.ingredientName
                )
                Text(
                    text = ingredient.quantity,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Preparation(
    preparationSteps: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.preparationSectionBackgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.label_preparation),
            modifier = Modifier
                .padding(vertical = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold
        )
        preparationSteps.forEachIndexed { index, preparationStep ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.preparationItemBackgroundColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = (index + 1).toString(),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = preparationStep,
                    lineHeight = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}