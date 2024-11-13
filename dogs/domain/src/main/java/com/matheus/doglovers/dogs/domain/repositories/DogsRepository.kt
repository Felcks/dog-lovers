package com.matheus.doglovers.dogs.domain.repositories

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getBreeds(): Flow<Resource<List<Breed>>>
}