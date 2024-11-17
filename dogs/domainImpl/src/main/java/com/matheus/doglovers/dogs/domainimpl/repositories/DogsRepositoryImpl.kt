package com.matheus.doglovers.dogs.domainimpl.repositories

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.datasources.DogsLocalDataSource
import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DogsRepositoryImpl(
    private val dogsRemoteDataSource: DogsRemoteDataSource,
    private val dogsLocalDataSource: DogsLocalDataSource,
): DogsRepository {
    override suspend fun getBreeds(): Flow<Resource<List<Breed>>> {
        return dogsRemoteDataSource.getBreeds()
    }

    override suspend fun getRandomDog(breed: Breed): Flow<Resource<Dog>> {
        return dogsRemoteDataSource.getRandomDog(breed)
    }

    override suspend fun getFavoriteDogs(): Flow<Resource<List<Dog>>> {
        return dogsLocalDataSource.getFavoriteDogs().map {
            Resource.Success(it)
        }
    }

    override suspend fun saveFavoriteDogs(dog: Dog): Flow<Resource<Dog>> {
        return dogsLocalDataSource.saveDog(dog).map {
            Resource.Success(it)
        }
    }

    override suspend fun removeFavoriteDog(dog: Dog): Flow<Resource<Dog>> {
        return dogsLocalDataSource.removeDog(dog).map {
            Resource.Success(it)
        }
    }
}