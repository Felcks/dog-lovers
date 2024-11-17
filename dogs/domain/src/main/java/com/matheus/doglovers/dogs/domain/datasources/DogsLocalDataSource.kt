package com.matheus.doglovers.dogs.domain.datasources

import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface DogsLocalDataSource {
    fun saveDog(dog: Dog): Flow<Dog>
    fun removeDog(dog: Dog): Flow<Dog>
    fun getFavoriteDogs(): Flow<List<Dog>>
}