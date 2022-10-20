package com.rahulghag.tasty.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.rahulghag.tasty.domain.models.Ingredient

data class IngredientDto(
    @SerializedName("ingredientName")
    val ingredientName: String,
    @SerializedName("quantity")
    val quantity: String
) {
    fun toIngredient(): Ingredient {
        return Ingredient(
            ingredientName = ingredientName,
            quantity = quantity
        )
    }
}