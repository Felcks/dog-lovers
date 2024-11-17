package com.matheus.doglovers.dogs.domainimpl.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domain.usecases.SaveFavoriteDogUseCase
import kotlinx.coroutines.flow.Flow

class SaveFavoriteDogUseCaseImpl(
    private val repository: DogsRepository
) : SaveFavoriteDogUseCase {
    override suspend fun invoke(dog: Dog, userEmail: String): Flow<Resource<Dog>> {
        return repository.saveFavoriteDogs(dog, userEmail)
    }
}