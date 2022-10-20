package com.rahulghag.tasty.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rahulghag.tasty.ui.features.recipe.detail.RecipeDetailsScreen
import com.rahulghag.tasty.ui.features.recipe.explore.ExploreRecipesScreen
import com.rahulghag.tasty.ui.navigation.Screen.Companion.NAV_ARG_RECIPE_ID

@Composable
fun SetupNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ExploreRecipes.route,
        modifier = Modifier
            .padding(paddingValues)
    ) {
        composable(route = Screen.ExploreRecipes.route) {
            ExploreRecipesScreen(
                onNavigateToDetailScreen = { id ->
                    navController.navigate(Screen.RecipeDetails.passRecipeId(id = id))
                }
            )
        }
        composable(
            route = Screen.RecipeDetails.route,
            arguments = listOf(navArgument(NAV_ARG_RECIPE_ID) { type = NavType.IntType })
        ) {
            RecipeDetailsScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}