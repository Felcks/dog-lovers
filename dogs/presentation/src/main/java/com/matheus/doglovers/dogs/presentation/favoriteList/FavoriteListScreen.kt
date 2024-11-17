package com.matheus.doglovers.dogs.presentation.favoriteList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.SubcomposeAsyncImage
import com.matheus.doglovers.core.presentation.R

@Composable
fun FavoriteListScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.handleScreenEvents(FavoriteListEvent.LoadFavoriteDogs)
    }

    FavoriteListScreenContent(
        uiState = uiState,
        modifier = modifier.background(Color.Transparent)
    )
}

@Composable
fun FavoriteListScreenContent(
    uiState: FavoriteListUIState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                tint = Color.White,
                contentDescription = null
            )
            Text(
                text = stringResource(com.matheus.doglovers.dogs.presentation.R.string.my_favorite_screen_title),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
            colors = CardDefaults.cardColors().copy(
                contentColor = Color.White,
                containerColor = Color.White,
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                when {
                    uiState.loading -> {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    uiState.errorMessage != null -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                uiState.errorMessage ?: "Unexpected error",
                                style = TextStyle(textAlign = TextAlign.Center),
                                fontSize = 18.sp
                            )
                            Box(modifier = Modifier.padding(vertical = 8.dp))
                            Button(onClick = { //loadAllBreeds.invoke()
                                 }) {
                                Text(stringResource(id = R.string.try_again))
                            }
                        }
                    }


                    uiState.dogs != null -> {
                        val dogs = uiState.dogs!!
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = modifier
                        ) {
                            items(
                                count = dogs.size,
                            ) { index: Int ->
                                val dog = dogs[index]

                                SubcomposeAsyncImage(
                                    model = dog.imageUrl,
                                    contentDescription = dog.breed.getVisualizationName(),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(110.dp)
                                        .clip(CircleShape.copy(all = CornerSize(16.dp)))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}