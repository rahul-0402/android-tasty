package com.rahulghag.tasty.domain.models

data class RecipeDetails(
    val featuredIn: String?,
    val id: Int,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val name: String,
    val nutrients: List<Nutrient>,
    val preparationSteps: List<String>,
    val presentedBy: String?,
    val rating: String?
)