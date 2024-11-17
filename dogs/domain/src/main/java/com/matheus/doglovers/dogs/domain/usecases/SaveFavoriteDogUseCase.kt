package com.matheus.doglovers.dogs.domain.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface SaveFavoriteDogUseCase {
    suspend operator fun invoke(dog: Dog): Flow<Resource<Boolean>>
}