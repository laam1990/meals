package com.example.yape.di

import com.example.yape.data.api.RemoteDataSource
import com.example.yape.repository.MealRepository
import com.example.yape.repository.MealRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealRepositoryImpl(
        remoteDataSource: RemoteDataSource
    ) = MealRepositoryImpl(
        remoteDataSource
    ) as MealRepository

}