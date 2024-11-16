package com.matheus.doglovers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matheus.doglovers.dogs.presentation.BreedSelectionRoute
import com.matheus.doglovers.dogs.presentation.breedSelectionScreen

@Composable
fun DogsNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BreedSelectionRoute,
        modifier = modifier
    ) {
        breedSelectionScreen()
    }
}