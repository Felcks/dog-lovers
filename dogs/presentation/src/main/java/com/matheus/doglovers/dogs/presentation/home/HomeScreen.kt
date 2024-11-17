package com.matheus.doglovers.dogs.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matheus.doglovers.core.presentation.TopLevelRoute
import com.matheus.doglovers.dogs.presentation.R
import com.matheus.doglovers.dogs.presentation.breedSelection.BreedSelectionRoute
import com.matheus.doglovers.dogs.presentation.breedSelection.breedSelectionScreen
import com.matheus.doglovers.dogs.presentation.favoriteList.FavoriteScreenRoute
import com.matheus.doglovers.dogs.presentation.favoriteList.favoriteListScreen
import com.matheus.doglovers.dogs.presentation.favoriteList.navigateToFavoriteScreen
import okhttp3.internal.wait

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topLevelRoutes = listOf(
        TopLevelRoute("Selection", BreedSelectionRoute, ImageVector.vectorResource(R.drawable.ic_dog)),
        TopLevelRoute("My Favourites", FavoriteScreenRoute, Icons.Default.Star)
    )

    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    topLevelRoutes.forEachIndexed { index, topLevelRoute ->
                        NavigationBarItem(
                            selected = navigationSelectedItem == index,
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(topLevelRoute.route)
                            },
                            icon = {
                                Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name)
                            },
                            label = {
                                Text(topLevelRoute.name)
                            },
                        )
                    }
                },
                containerColor = Color.White,
                contentColor = Color.White
            )
        },
    ) { innerPadding ->
        Image(
            painter = painterResource(com.matheus.doglovers.core.presentation.R.drawable.app_background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .height(24.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                IconButton(
                    onClick = onLogout,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(com.matheus.doglovers.core.presentation.R.drawable.ic_envelope),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }

            NavHost(
                navController = navController,
                startDestination = BreedSelectionRoute,
                modifier = modifier.fillMaxSize()
            ) {
                breedSelectionScreen(
                    onLogout = { onLogout() },
                    modifier = Modifier.fillMaxSize()
                )
                favoriteListScreen(
                    onLogout = { onLogout() }
                )
            }

            Text("aeiufhaifhaufhaufghb")
        }
    }
}