package com.matheus.doglovers.dogs.persistence.mappers

import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.persistence.models.BreedEntity

fun Breed.toEntity() : BreedEntity {
    return BreedEntity(
        name = name,
        specification = specification
    )
}