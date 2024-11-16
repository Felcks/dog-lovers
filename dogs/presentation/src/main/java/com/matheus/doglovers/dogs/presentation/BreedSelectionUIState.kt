package com.matheus.doglovers.dogs.presentation

import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog

data class BreedSelectionUIState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var breeds: List<Breed>? = null,
    val selectedBreed: Breed? = null,
    val currentDog: Dog? = null
)