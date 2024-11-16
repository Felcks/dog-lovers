package com.matheus.doglovers.dogs.presentation

sealed class BreedSelectionEvent {
    data object loadBreeds : BreedSelectionEvent()
}