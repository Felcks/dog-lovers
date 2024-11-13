package com.matheus.doglovers.dogs.network.datasources

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.core.network.safeApiCall
import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.network.api.DogsService
import com.matheus.doglovers.dogs.network.models.BreedResponse
import com.matheus.doglovers.dogs.network.models.BreedsResponse
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
                        BreedResponse(it.key)
                    )
                } else {
                    it.value.map { value ->
                        BreedResponse("$value ${it.key}")
                    }
                }
            } ?: listOf()
        }
    }
}