package com.matheus.doglovers.dogs.presentation.favoriteList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteListScreen(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Text(
            "THIS IS FAVORITE LIST", modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}