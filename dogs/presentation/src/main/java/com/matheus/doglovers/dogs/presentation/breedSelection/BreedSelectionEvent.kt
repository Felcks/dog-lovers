package com.matheus.doglovers.dogs.presentation.breedSelection

import com.google.firebase.auth.FirebaseUser
import com.matheus.doglovers.dogs.domain.models.Breed

sealed class BreedSelectionEvent {
    data class SetUser(val user: FirebaseUser): BreedSelectionEvent()
    data object LoadAllBreeds : BreedSelectionEvent()
    data class LoadRandomDogImage(val breed: Breed) : BreedSelectionEvent()
    data object ShuffleImageForSelectedBreed : BreedSelectionEvent()
    data object FavoriteOrUnfavoriteCurrentDog : BreedSelectionEvent()
}