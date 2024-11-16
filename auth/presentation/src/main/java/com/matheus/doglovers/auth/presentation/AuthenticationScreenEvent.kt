package com.matheus.doglovers.auth.presentation

sealed class AuthenticationScreenEvent {
    data object CheckAuthState : AuthenticationScreenEvent()
    data class SigninUser(val email: String, val password: String) : AuthenticationScreenEvent()
}