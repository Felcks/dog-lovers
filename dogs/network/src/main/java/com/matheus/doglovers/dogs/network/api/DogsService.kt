package com.matheus.doglovers.dogs.network.api

import com.matheus.doglovers.dogs.network.models.BreedsResponse
import com.matheus.doglovers.dogs.network.models.RandomDogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<BreedsResponse>

    @GET("breed/{breed}/images/random")
    suspend fun getRandomDog(
        @Path("breed", encoded = true) breed: String
    ): Response<RandomDogResponse>
}