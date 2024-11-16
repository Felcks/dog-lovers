package com.matheus.doglovers.dogs.network.di

import com.matheus.doglovers.dogs.network.api.DogsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideDogsService(@DogsRetrofit retrofit: Retrofit): DogsService =
        retrofit.create(DogsService::class.java)
}