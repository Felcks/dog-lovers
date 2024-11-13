package com.matheus.doglovers.dogs.domainimpl.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domain.usecases.ListBreedsUseCase
import kotlinx.coroutines.flow.Flow

class ListBreedsUseCaseImpl(
    private val dogsRepository: DogsRepository
): ListBreedsUseCase {
    override suspend fun invoke(): Flow<Resource<List<Breed>>> {
        return dogsRepository.getBreeds()
    }
}