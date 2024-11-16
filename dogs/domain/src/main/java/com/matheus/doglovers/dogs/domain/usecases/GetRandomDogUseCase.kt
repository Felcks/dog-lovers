package com.matheus.doglovers.dogs.domain.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface GetRandomDogUseCase {
    suspend operator fun invoke(breed: Breed): Flow<Resource<Dog>>
}