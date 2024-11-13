package com.matheus.doglovers.dogs.network.models

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("message")
    val message: Map<String, List<String>>
)