package com.matheus.doglovers.dogs.domain.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import kotlinx.coroutines.flow.Flow

interface ListBreedsUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Breed>>>
}