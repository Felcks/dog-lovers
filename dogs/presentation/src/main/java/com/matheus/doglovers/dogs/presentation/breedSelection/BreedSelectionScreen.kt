package com.matheus.doglovers.dogs.presentation.breedSelection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.SubcomposeAsyncImage
import com.matheus.doglovers.core.presentation.R
import com.matheus.doglovers.core.presentation.theme.DogLoversTheme
import com.matheus.doglovers.dogs.domain.models.Breed

@Composable
fun BreedSelectionScreen(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BreedSelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.handleScreenEvents(BreedSelectionEvent.CheckAuthState)
    }

    LaunchedEffect(uiState.user) {
        if (uiState.user != null) {
            viewModel.handleScreenEvents(BreedSelectionEvent.LoadAllBreeds)
        } else {
            onLogout.invoke()
        }
    }

    BreedSelectionScreenContent(
        uiState = uiState,
        loadAllBreeds = { viewModel.handleScreenEvents(BreedSelectionEvent.LoadAllBreeds) },
        onBreedSelected = { viewModel.handleScreenEvents(BreedSelectionEvent.LoadRandomDogImage(it)) },
        onShuffleClick = { viewModel.handleScreenEvents(BreedSelectionEvent.ShuffleImageForSelecteBreed) },
        onSignoutClick = { viewModel.handleScreenEvents(BreedSelectionEvent.Signout) },
        onFavoriteClick = { viewModel.handleScreenEvents(BreedSelectionEvent.SaveCurrentDogAsFavorite) },
        modifier = modifier.background(Color.Transparent)
    )
}

@Composable
fun BreedSelectionScreenContent(
    uiState: BreedSelectionUIState,
    loadAllBreeds: () -> Unit,
    onBreedSelected: (Breed) -> Unit,
    onShuffleClick: () -> Unit,
    onSignoutClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(com.matheus.doglovers.dogs.presentation.R.drawable.ic_dog),
                tint = Color.White,
                contentDescription = null
            )
            Text(
                text = stringResource(com.matheus.doglovers.dogs.presentation.R.string.dog_selection_screen_title),
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
                            Button(onClick = { loadAllBreeds.invoke() }) {
                                Text(stringResource(id = R.string.try_again))
                            }
                        }
                    }


                    uiState.breeds != null -> {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 32.dp)
                                .fillMaxSize()
                        ) {
                            Text(
                                stringResource(com.matheus.doglovers.dogs.presentation.R.string.dog_selection_screen_breed_label),
                                color = Color.Black
                            )
                            BreedSelectionDropDown(
                                uiState.breeds!!,
                                uiState.selectedBreed,
                                onSelected = onBreedSelected::invoke,
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.padding(top = 32.dp))

                            uiState.currentDog?.let {
                                SubcomposeAsyncImage(
                                    model = it.imageUrl,
                                    contentDescription = it.breed.getVisualizationName(),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(horizontal = 32.dp)
                                        .fillMaxWidth()
                                        .heightIn(max = 175.dp)
                                        .clip(CircleShape.copy(all = CornerSize(16.dp)))
                                        .clickable {
                                            onFavoriteClick.invoke()
                                        }
                                )
                            }

                            Spacer(modifier = Modifier.padding(top = 32.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                Button(
                                    onClick = onShuffleClick,
                                    enabled = uiState.currentDog != null,
                                    colors = ButtonDefaults.buttonColors()
                                        .copy(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        ),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
                                    ) {
                                        Text(
                                            stringResource(com.matheus.doglovers.dogs.presentation.R.string.dog_selection_screen_shuffle_button),
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.padding(start = 8.dp))
                                        Icon(
                                            imageVector = ImageVector.vectorResource(com.matheus.doglovers.dogs.presentation.R.drawable.ic_update),
                                            tint = Color.White,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedSelectionDropDown(
    breeds: List<Breed>,
    selectedBreed: Breed?,
    onSelected: (Breed) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption: Breed? by remember { mutableStateOf(selectedBreed) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        TextField(
            value = selectedOption?.getVisualizationName().orEmpty(),
            label = { Text(stringResource(com.matheus.doglovers.dogs.presentation.R.string.dog_selection_screen_breed_dropdown_label)) },
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            breeds.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.getVisualizationName()) },
                    onClick = {
                        selectedOption = selectionOption
                        expanded = false
                        onSelected.invoke(selectionOption)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun BreedSelectionScreenContentPreview() {
    DogLoversTheme {
        BreedSelectionScreenContent(
            BreedSelectionUIState(
                breeds = listOf(
                    object : Breed {
                        override val name: String = "Name"
                        override val specification: String = "1"

                    },
                    object : Breed {
                        override val name: String = "Name"
                        override val specification: String = "2"
                    },
                ),
            ),
            {},
            {},
            {},
            {},
            {}
        )
    }
}

@Preview
@Composable
private fun BreedSelectionDropDownPreview() {
    DogLoversTheme {
        BreedSelectionDropDown(
            listOf(
                object : Breed {
                    override val name: String = "Name"
                    override val specification: String = "1"

                },
                object : Breed {
                    override val name: String = "Name"
                    override val specification: String = "1"

                },
            ),
            selectedBreed = null,
            onSelected = {},
            modifier = Modifier
        )
    }
}