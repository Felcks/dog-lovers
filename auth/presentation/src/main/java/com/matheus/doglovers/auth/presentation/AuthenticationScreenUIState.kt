package com.matheus.doglovers.auth.presentation

import com.google.firebase.auth.FirebaseUser

data class AuthenticationScreenUIState(
    val user: FirebaseUser? = null,
    val authenticationError: AuthenticationError? = null,
)

sealed interface AuthenticationError {
    data object InvalidEmailAndPasswordCombination : AuthenticationError
    data object UnexpectedError : AuthenticationError
}