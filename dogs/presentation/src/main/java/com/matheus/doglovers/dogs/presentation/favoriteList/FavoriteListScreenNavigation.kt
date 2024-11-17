package com.matheus.doglovers.dogs.presentation.favoriteList

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseUser

const val FavoriteScreenRoute = "favorite_screen_navigation"

fun NavController.navigateToFavoriteScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(FavoriteScreenRoute, navOptions)
}

fun NavGraphBuilder.favoriteListScreen(
    firebaseUser: FirebaseUser,
) {
    composable(
        FavoriteScreenRoute
    ) {
        FavoriteListScreen(firebaseUser)
    }
}