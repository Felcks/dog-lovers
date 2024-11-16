package com.matheus.doglovers.dogs.network.models

import com.matheus.doglovers.dogs.domain.models.Breed

data class BreedResponse(
    override val name: String,
    override val specification: String?
) : Breed