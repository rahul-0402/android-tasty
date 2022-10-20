package com.rahulghag.tasty.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.rahulghag.tasty.domain.models.RecipeDetails

data class RecipeDetailsDto(
    @SerializedName("featuredIn")
    val featuredIn: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientDto>,
    @SerializedName("name")
    val name: String,
    @SerializedName("nutrients")
    val nutrients: List<NutrientDto>,
    @SerializedName("preparationSteps")
    val preparationSteps: List<String>,
    @SerializedName("presentedBy")
    val presentedBy: String,
    @SerializedName("rating")
    val rating: String
) {
    fun toRecipeDetails(): RecipeDetails {
        return RecipeDetails(
            featuredIn = featuredIn,
            id = id,
            imageUrl = imageUrl,
            ingredients = ingredients.map { it.toIngredient() },
            name = name,
            nutrients = nutrients.map { it.toNutrient() },
            preparationSteps = preparationSteps,
            presentedBy = presentedBy,
            rating = rating
        )
    }
}