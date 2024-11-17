package com.matheus.doglovers.dogs.domain.datasources

import com.matheus.doglovers.dogs.domain.models.Dog
import kotlinx.coroutines.flow.Flow

interface DogsLocalDataSource {
    fun saveDog(dog: Dog, userEmail: String): Flow<Dog>
    fun removeDog(dog: Dog, userEmail: String): Flow<Dog>
    fun getFavoriteDogs(userEmail: String): Flow<List<Dog>>
}