package com.rahulghag.tasty.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.rahulghag.tasty.domain.models.Recipe

data class RecipeDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
) {
    fun toRecipe(): Recipe {
        return Recipe(
            id = id,
            imageUrl = imageUrl,
            name = name
        )
    }
}