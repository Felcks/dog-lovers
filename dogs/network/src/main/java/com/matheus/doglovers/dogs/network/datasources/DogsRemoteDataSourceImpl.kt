package com.matheus.doglovers.dogs.network.datasources

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.core.network.safeApiCall
import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.network.api.DogsService
import com.matheus.doglovers.dogs.network.models.BreedResponse
import com.matheus.doglovers.dogs.network.models.DogResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class DogsRemoteDataSourceImpl(
    private val dogsService: DogsService,
    private val dispatcher: CoroutineDispatcher,
) : DogsRemoteDataSource {
    override suspend fun getBreeds(): Flow<Resource<List<BreedResponse>>> {
        return safeApiCall(dispatcher) {
            dogsService.getBreeds().body()?.message?.flatMap {
                if (it.value.isEmpty()) {
                    listOf(
                        BreedResponse(name = it.key, specification = null)
                    )
                } else {
                    it.value.map { value ->
                        BreedResponse(name = it.key, specification = value)
                    }
                }
            } ?: listOf()
        }
    }

    override suspend fun getRandomDog(breed: Breed): Flow<Resource<Dog>> {
        return safeApiCall(dispatcher) {
            dogsService.getRandomDog(breed.getResourceName()).body()?.message.let {
                DogResponse(
                    breed = breed,
                    imageUrl = it.orEmpty(),
                    isFavorite = false,
                    userEmail = ""
                )
            }
        }
    }
}