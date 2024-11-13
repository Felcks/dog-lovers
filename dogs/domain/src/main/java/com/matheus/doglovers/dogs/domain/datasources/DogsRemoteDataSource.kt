package com.matheus.doglovers.dogs.domain.datasources

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import kotlinx.coroutines.flow.Flow

interface DogsRemoteDataSource {
    suspend fun getBreeds(): Flow<Resource<List<Breed>>>
}