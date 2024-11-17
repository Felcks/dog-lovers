package com.matheus.doglovers.dogs.domain.repositories

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getBreeds(): Flow<Resource<List<Breed>>>
    suspend fun getRandomDog(breed: Breed): Flow<Resource<Dog>>
    suspend fun getFavoriteDogs(userEmail: String): Flow<Resource<List<Dog>>>
    suspend fun saveFavoriteDogs(dog: Dog, userEmail: String): Flow<Resource<Dog>>
    suspend fun removeFavoriteDog(dog: Dog, userEmail: String): Flow<Resource<Dog>>
}