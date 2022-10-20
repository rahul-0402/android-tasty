package com.rahulghag.tasty.ui.navigation

sealed class Screen(val route: String) {
    object ExploreRecipes : Screen(
        route = "explore_recipes",
    )

    object RecipeDetails : Screen(route = "recipe_details/{$NAV_ARG_RECIPE_ID}") {
        fun passRecipeId(id: Int): String {
            return "recipe_details/${id}"
        }
    }

    companion object {
        const val NAV_ARG_RECIPE_ID = "id"
    }
}