package com.matheus.doglovers.dogs.presentation.favoriteList

import com.google.firebase.auth.FirebaseUser
import com.matheus.doglovers.dogs.domain.models.Dog

data class FavoriteListUIState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var dogs: List<Dog>? = null,
    val user: FirebaseUser? = null
)