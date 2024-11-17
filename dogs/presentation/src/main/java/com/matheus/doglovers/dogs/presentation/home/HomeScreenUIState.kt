package com.matheus.doglovers.dogs.presentation.home

import com.google.firebase.auth.FirebaseUser

data class HomeScreenUIState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    val user: FirebaseUser? = null
)