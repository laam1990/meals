package com.example.yape.di

import com.example.yape.repository.MealMapperFacade
import com.example.yape.repository.MealMapperFacadeImpl
import com.example.yape.ui.mapper.MealFromIngredientMapper
import com.example.yape.ui.mapper.MealMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMyActivityMapperFacadeImpl(
        mealMapper: MealMapper,
        mealFromIngredientMapper: MealFromIngredientMapper
    ) = MealMapperFacadeImpl(
        mealMapper, mealFromIngredientMapper
    ) as MealMapperFacade
}
