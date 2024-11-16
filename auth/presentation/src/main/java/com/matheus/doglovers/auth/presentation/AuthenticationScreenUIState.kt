package com.matheus.doglovers.auth.presentation

import com.google.firebase.auth.FirebaseUser

data class AuthenticationScreenUIState(
    val user: FirebaseUser? = null
)