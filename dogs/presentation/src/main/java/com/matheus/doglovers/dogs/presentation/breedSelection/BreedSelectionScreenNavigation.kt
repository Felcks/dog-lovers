package com.matheus.doglovers.dogs.presentation.breedSelection

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val BreedSelectionRoute = "breed_selection_navigation"

fun NavController.navigateToBreedSelectionScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(BreedSelectionRoute, navOptions)
}

fun NavGraphBuilder.breedSelectionScreen(
    modifier: Modifier = Modifier,
) {
    composable(
        BreedSelectionRoute
    ) {
        BreedSelectionScreen(modifier)
    }
}