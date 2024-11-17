package com.matheus.doglovers.dogs.domainimpl.di

import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domain.usecases.GetFavoriteDogsUseCase
import com.matheus.doglovers.dogs.domain.usecases.GetRandomDogUseCase
import com.matheus.doglovers.dogs.domain.usecases.ListBreedsUseCase
import com.matheus.doglovers.dogs.domainimpl.usecases.GetFavoriteDogsUseCaseImpl
import com.matheus.doglovers.dogs.domainimpl.usecases.GetRandomDogUseCaseImpl
import com.matheus.doglovers.dogs.domainimpl.usecases.ListBreedsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideListBreedsUseCase(
        dogsRepository: DogsRepository
    ): ListBreedsUseCase {
        return ListBreedsUseCaseImpl(dogsRepository)
    }

    @Provides
    @Singleton
    fun provideGetRandomDogUseCase(
        dogsRepository: DogsRepository
    ): GetRandomDogUseCase {
        return GetRandomDogUseCaseImpl(dogsRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteDogsUseCase(
        dogsRepository: DogsRepository
    ): GetFavoriteDogsUseCase {
        return GetFavoriteDogsUseCaseImpl(dogsRepository)
    }
}