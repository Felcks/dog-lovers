package com.matheus.doglovers.dogs.presentation

import com.matheus.doglovers.dogs.domain.models.Breed

data class BreedSelectionUIState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var breeds: List<Breed>? = null
)