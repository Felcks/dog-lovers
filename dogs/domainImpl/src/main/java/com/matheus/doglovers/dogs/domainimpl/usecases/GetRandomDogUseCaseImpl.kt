package com.matheus.doglovers.dogs.domainimpl.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domain.usecases.GetRandomDogUseCase
import kotlinx.coroutines.flow.Flow

class GetRandomDogUseCaseImpl(
    private val dogsRepository: DogsRepository,
): GetRandomDogUseCase {
    override suspend fun invoke(breed: Breed): Flow<Resource<Dog>> {
        return dogsRepository.getRandomDog(breed)
    }
}