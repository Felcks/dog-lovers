package com.matheus.doglovers.dogs.domain.models

interface Dog {
    val breed: Breed
    val imageUrl: String
    val isFavorite: Boolean
}