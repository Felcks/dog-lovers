package com.matheus.doglovers.dogs.domainimpl.repositories

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import kotlinx.coroutines.flow.Flow

class DogsRepositoryImpl(
    private val dogsRemoteDataSource: DogsRemoteDataSource
): DogsRepository {
    override suspend fun getBreeds(): Flow<Resource<List<Breed>>> {
        return dogsRemoteDataSource.getBreeds()
    }

    override suspend fun getRandomDog(breed: Breed): Flow<Resource<Dog>> {
        return dogsRemoteDataSource.getRandomDog(breed)
    }
}