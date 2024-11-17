package com.matheus.doglovers.dogs.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val HomeScreenRoute = "home_screen_navigation"

fun NavController.navigateToHomeScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(HomeScreenRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onLogout: () -> Unit
) {
    composable(
        HomeScreenRoute
    ) {
        HomeScreen(onLogout)
    }
}