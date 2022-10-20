package com.rahulghag.tasty.data.repositories

import com.rahulghag.tasty.R
import com.rahulghag.tasty.data.remote.TastyApi
import com.rahulghag.tasty.domain.models.Recipe
import com.rahulghag.tasty.domain.models.RecipeDetails
import com.rahulghag.tasty.domain.repositories.RecipeRepository
import com.rahulghag.tasty.utils.Resource
import com.rahulghag.tasty.utils.UiMessage
import retrofit2.HttpException
import java.io.IOException

class RecipeRepositoryImpl(
    private val tastyApi: TastyApi
) : RecipeRepository {
    override suspend fun fetchRecipeList(): Resource<List<Recipe>> {
        return try {
            val response = tastyApi.fetchRecipeList()
            Resource.Success(data = response.map { it.toRecipe() })
        } catch (e: IOException) {
            Resource.Error(message = UiMessage.StringResource(R.string.error_no_internet_connection))
        } catch (e: HttpException) {
            Resource.Error(message = UiMessage.StringResource(R.string.error_something_went_wrong))
        }
    }

    override suspend fun fetchRecipeDetailsById(id: Int): Resource<RecipeDetails> {
        return try {
            val response = tastyApi.fetchRecipeDetailsById(id = id)
            Resource.Success(data = response.toRecipeDetails())
        } catch (e: IOException) {
            Resource.Error(message = UiMessage.StringResource(R.string.error_no_internet_connection))
        } catch (e: HttpException) {
            Resource.Error(message = UiMessage.StringResource(R.string.error_something_went_wrong))
        }
    }
}