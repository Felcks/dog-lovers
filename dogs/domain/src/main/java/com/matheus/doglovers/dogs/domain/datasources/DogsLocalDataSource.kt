package com.matheus.doglovers.dogs.domain.datasources

import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface DogsLocalDataSource {
    fun saveDog(dog: Dog): Flow<Boolean>
    fun getFavoriteDogs(): Flow<List<Dog>>
}