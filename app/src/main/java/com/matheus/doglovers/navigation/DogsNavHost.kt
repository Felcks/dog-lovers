package com.matheus.doglovers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matheus.doglovers.auth.presentation.AuthenticationRoute
import com.matheus.doglovers.auth.presentation.authenticationScreen
import com.matheus.doglovers.auth.presentation.navigateToAuthenticationScreen
import com.matheus.doglovers.dogs.presentation.breedSelection.breedSelectionScreen
import com.matheus.doglovers.dogs.presentation.breedSelection.navigateToBreedSelectionScreen
import com.matheus.doglovers.dogs.presentation.home.homeScreen
import com.matheus.doglovers.dogs.presentation.home.navigateToHomeScreen

@Composable
fun DogsNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthenticationRoute,
        modifier = modifier
    ) {
        authenticationScreen(
            onAuthenticationSuccessful = { navController.navigateToHomeScreen() }
        )
        homeScreen(
            onLogout = { navController.navigateToAuthenticationScreen() }
        )
    }
}