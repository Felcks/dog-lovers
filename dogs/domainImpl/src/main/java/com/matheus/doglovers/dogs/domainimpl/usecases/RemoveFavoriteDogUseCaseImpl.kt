package com.matheus.doglovers.dogs.domainimpl.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domain.usecases.RemoveFavoriteDogUseCase
import com.matheus.doglovers.dogs.domain.usecases.SaveFavoriteDogUseCase
import kotlinx.coroutines.flow.Flow

class RemoveFavoriteDogUseCaseImpl(
    private val repository: DogsRepository
) : RemoveFavoriteDogUseCase {
    override suspend fun invoke(dog: Dog): Flow<Resource<Dog>> {
        return repository.removeFavoriteDog(dog)
    }
}