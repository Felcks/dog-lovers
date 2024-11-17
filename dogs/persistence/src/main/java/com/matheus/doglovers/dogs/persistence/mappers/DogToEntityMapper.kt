package com.matheus.doglovers.dogs.persistence.mappers


import com.matheus.doglovers.dogs.domain.models.Dog
import com.matheus.doglovers.dogs.persistence.models.DogEntity

fun Dog.toEntity() : DogEntity {
    return DogEntity(
        imageUrl = imageUrl,
        breed = breed.toEntity(),
        isFavorite = isFavorite,
        userEmail = userEmail
    )
}