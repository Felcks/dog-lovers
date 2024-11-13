package com.matheus.doglovers.dogs.network.api

import com.matheus.doglovers.dogs.network.models.BreedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<BreedsResponse>
}