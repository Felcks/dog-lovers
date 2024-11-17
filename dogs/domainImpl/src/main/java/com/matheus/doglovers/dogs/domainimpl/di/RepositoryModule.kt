package com.matheus.doglovers.dogs.domainimpl.di

import com.matheus.doglovers.dogs.domain.datasources.DogsLocalDataSource
import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domainimpl.repositories.DogsRepositoryImpl
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
    fun provideDogsRepository(
        dogsRemoteDataSource: DogsRemoteDataSource,
        dogsLocalDataSource: DogsLocalDataSource,
    ): DogsRepository {
        return DogsRepositoryImpl(dogsRemoteDataSource, dogsLocalDataSource)
    }
}