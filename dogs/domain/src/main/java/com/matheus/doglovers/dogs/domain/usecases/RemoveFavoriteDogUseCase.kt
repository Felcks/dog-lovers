package com.matheus.doglovers.dogs.domain.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface RemoveFavoriteDogUseCase {
    suspend operator fun invoke(dog: Dog, userEmail: String): Flow<Resource<Dog>>
}