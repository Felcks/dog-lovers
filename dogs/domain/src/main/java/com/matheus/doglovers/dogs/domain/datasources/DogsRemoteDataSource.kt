package com.matheus.doglovers.dogs.domain.datasources

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRemoteDataSource {
    suspend fun getBreeds(): Flow<Resource<List<Breed>>>
    suspend fun getRandomDog(breed: Breed): Flow<Resource<Dog>>
}