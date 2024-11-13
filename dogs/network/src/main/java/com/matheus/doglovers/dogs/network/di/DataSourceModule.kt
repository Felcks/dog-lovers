package com.matheus.doglovers.dogs.network.di

import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.network.api.DogsService
import com.matheus.doglovers.dogs.network.datasources.DogsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideDogsRemoteDataSource(
        dogsService: DogsService,
    ): DogsRemoteDataSource {
        return DogsRemoteDataSourceImpl(dogsService = dogsService, dispatcher = Dispatchers.IO)
    }
}