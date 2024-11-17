package com.matheus.doglovers.dogs.network.models

import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.models.Dog

class DogResponse(
    override val breed: Breed,
    override val imageUrl: String,
    override val isFavorite: Boolean
) : Dog