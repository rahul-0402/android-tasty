package com.rahulghag.tasty.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.rahulghag.tasty.domain.models.Nutrient

data class NutrientDto(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("nutrientName")
    val nutrientName: String
) {
    fun toNutrient(): Nutrient {
        return Nutrient(
            amount = amount,
            nutrientName = nutrientName
        )
    }
}