package com.matheus.doglovers.dogs.network.models

import com.google.gson.annotations.SerializedName

class RandomDogResponse(
    @SerializedName("message")
    val message: String?
)