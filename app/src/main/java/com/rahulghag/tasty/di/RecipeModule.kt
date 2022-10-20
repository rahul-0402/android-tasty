package com.rahulghag.tasty.di

import com.rahulghag.tasty.data.remote.TastyApi
import com.rahulghag.tasty.data.repositories.RecipeRepositoryImpl
import com.rahulghag.tasty.domain.repositories.RecipeRepository
import com.rahulghag.tasty.domain.usecases.FetchRecipeDetailsUseCase
import com.rahulghag.tasty.domain.usecases.FetchRecipeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(tastyApi: TastyApi): RecipeRepository {
        return RecipeRepositoryImpl(tastyApi)
    }

    @Provides
    @Singleton
    fun provideFetchRecipeListUseCase(recipeRepository: RecipeRepository): FetchRecipeListUseCase {
        return FetchRecipeListUseCase(recipeRepository)
    }

    @Provides
    @Singleton
    fun provideFetchRecipeDetailsUseCase(recipeRepository: RecipeRepository): FetchRecipeDetailsUseCase {
        return FetchRecipeDetailsUseCase(recipeRepository)
    }
}