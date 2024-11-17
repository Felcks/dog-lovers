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
    override fun saveDog(dog: Dog, userEmail: String): Flow<Dog> {
        return flow {
            val entityDog = dog.toEntity().copy(isFavorite = true, userEmail = userEmail)
            appDatabase.favoriteDogsDao().insertAll(entityDog)
            emit(entityDog)
        }
    }

    override fun removeDog(dog: Dog, userEmail: String): Flow<Dog> {
        return flow {
            val entityDog = dog.toEntity().copy(isFavorite = false, userEmail = userEmail)
            appDatabase.favoriteDogsDao().insertAll(entityDog)
            emit(entityDog)
        }
    }

    override fun getFavoriteDogs(userEmail: String): Flow<List<Dog>> {
        return appDatabase.favoriteDogsDao().getFavorites(userEmail)
    }
}