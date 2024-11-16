package com.matheus.doglovers.auth.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val AuthenticationRoute = "authentication_navigation"

fun NavController.navigateToAuthenticationScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(AuthenticationRoute, navOptions)
}

fun NavGraphBuilder.authenticationScreen(
    onAuthenticationSuccessful: () -> Unit
) {
    composable(
        AuthenticationRoute
    ) {
        AuthenticationScreen(onAuthenticationSuccessful)
    }
}