package com.matheus.doglovers.dogs.persistence.di

import com.matheus.doglovers.dogs.domain.datasources.DogsLocalDataSource
import com.matheus.doglovers.dogs.persistence.database.AppDatabase
import com.matheus.doglovers.dogs.persistence.datasources.DogsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideDogsLocalDataSource(
        appDatabase: AppDatabase,
    ): DogsLocalDataSource {
        return DogsLocalDataSourceImpl(appDatabase)
    }
}