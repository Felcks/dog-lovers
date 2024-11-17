package com.matheus.doglovers.dogs.presentation.favoriteList

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val FavoriteScreenRoute = "favorite_screen_navigation"

fun NavController.navigateToFavoriteScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(FavoriteScreenRoute, navOptions)
}

fun NavGraphBuilder.favoriteListScreen() {
    composable(
        FavoriteScreenRoute
    ) {
        FavoriteListScreen()
    }
}