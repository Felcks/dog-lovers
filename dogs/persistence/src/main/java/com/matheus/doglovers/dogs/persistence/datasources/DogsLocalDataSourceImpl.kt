package com.matheus.doglovers.dogs.persistence.datasources

import com.matheus.doglovers.dogs.domain.datasources.DogsLocalDataSource
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.persistence.database.AppDatabase
import com.matheus.doglovers.dogs.persistence.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogsLocalDataSourceImpl(
    private val appDatabase: AppDatabase,
) : DogsLocalDataSource {
    override fun saveDog(dog: Dog): Flow<Dog> {
        return flow {
            val entityDog = dog.toEntity().copy(isFavorite = true)
            appDatabase.favoriteDogsDao().insertAll(entityDog)
            emit(entityDog)
        }
    }

    override fun removeDog(dog: Dog): Flow<Dog> {
        return flow {
            val entityDog = dog.toEntity().copy(isFavorite = false)
            appDatabase.favoriteDogsDao().insertAll(entityDog)
            emit(entityDog)
        }
    }

    override fun getFavoriteDogs(): Flow<List<Dog>> {
        return appDatabase.favoriteDogsDao().getFavorites()
    }
}