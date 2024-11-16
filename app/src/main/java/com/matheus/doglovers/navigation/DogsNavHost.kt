package com.matheus.doglovers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matheus.doglovers.auth.presentation.AuthenticationRoute
import com.matheus.doglovers.auth.presentation.authenticationScreen
import com.matheus.doglovers.auth.presentation.navigateToAuthenticationScreen
import com.matheus.doglovers.dogs.presentation.breedSelectionScreen
import com.matheus.doglovers.dogs.presentation.navigateToBreedSelectionScreen

@Composable
fun DogsNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthenticationRoute,
        modifier = modifier
    ) {
        authenticationScreen(
            onAuthenticationSuccessful = { navController.navigateToBreedSelectionScreen() }
        )
        breedSelectionScreen(
            onLogout = { navController.navigateToAuthenticationScreen() }
        )
    }
}