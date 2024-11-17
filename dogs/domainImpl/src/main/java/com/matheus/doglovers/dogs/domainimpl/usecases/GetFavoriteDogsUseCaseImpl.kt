package com.matheus.doglovers.dogs.domainimpl.usecases

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import com.matheus.doglovers.dogs.domain.usecases.GetFavoriteDogsUseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteDogsUseCaseImpl(
    private val repository: DogsRepository
) : GetFavoriteDogsUseCase {
    override suspend fun invoke(): Flow<Resource<List<Dog>>> {
        return repository.getFavoriteDogs()
    }
}