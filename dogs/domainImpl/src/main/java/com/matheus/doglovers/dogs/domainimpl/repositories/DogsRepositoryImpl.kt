package com.matheus.doglovers.dogs.domainimpl.repositories

import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.datasources.DogsLocalDataSource
import com.matheus.doglovers.dogs.domain.datasources.DogsRemoteDataSource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.domain.repositories.DogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DogsRepositoryImpl(
    private val dogsRemoteDataSource: DogsRemoteDataSource,
    private val dogsLocalDataSource: DogsLocalDataSource,
): DogsRepository {
    override suspend fun getBreeds(): Flow<Resource<List<Breed>>> {
        return dogsRemoteDataSource.getBreeds()
    }

    override suspend fun getRandomDog(breed: Breed): Flow<Resource<Dog>> {
        return dogsRemoteDataSource.getRandomDog(breed)
    }

    override suspend fun getFavoriteDogs(): Flow<Resource<List<Dog>>> {
        return dogsLocalDataSource.getFavoriteDogs().map {
            Resource.Success(it)
        }
    }

    override suspend fun saveFavoriteDogs(dog: Dog): Flow<Resource<Boolean>> {
        return dogsLocalDataSource.saveDog(dog).map {
            if(it) {
                Resource.Success(true)
            } else {
                Resource.Error("Fail")
            }
        }
    }


    /*
    return flowOf(Resource.Success(
            listOf(
                object : Dog {
                    override val breed: Breed
                        get() = object : Breed {
                            override val name: String = "Kelpie"
                            override val specification: String? = "Austrian"
                        }
                    override val imageUrl: String = "https://images.dog.ceo/breeds/australian-kelpie/Resized_20200416_142905_108884348190285.jpg"
                },
                object : Dog {
                    override val breed: Breed
                        get() = object : Breed {
                            override val name: String = "Beagle"
                            override val specification: String? = null
                        }
                    override val imageUrl: String = "https://images.dog.ceo/breeds/beagle/n02088364_14863.jpg"
                },
                object : Dog {
                    override val breed: Breed
                        get() = object : Breed {
                            override val name: String = "Border"
                            override val specification: String? = "Collie"
                        }
                    override val imageUrl: String = "https://images.dog.ceo/breeds/collie-border/n02106166_4625.jpg"
                }
            )
        ))
     */
}