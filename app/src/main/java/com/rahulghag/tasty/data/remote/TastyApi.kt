package com.rahulghag.tasty.data.remote

import com.rahulghag.tasty.data.remote.dtos.RecipeDetailsDto
import com.rahulghag.tasty.data.remote.dtos.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TastyApi {
    @GET("tasty/recipes")
    suspend fun fetchRecipeList(): List<RecipeDto>

    @GET("tasty/recipes/{id}")
    suspend fun fetchRecipeDetailsById(@Path("id") id: Int): RecipeDetailsDto

    companion object {
        const val BASE_URL = "https://floating-sands-46828.herokuapp.com/"
    }
}