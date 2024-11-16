package com.matheus.doglovers.dogs.presentation

import com.matheus.doglovers.dogs.domain.models.Breed

sealed class BreedSelectionEvent {
    data object LoadAllBreeds : BreedSelectionEvent()
    data class LoadRandomDogImage(val breed: Breed) : BreedSelectionEvent()
    data object ShuffleImageForSelecteBreed : BreedSelectionEvent()
}