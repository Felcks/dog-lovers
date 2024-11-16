package com.matheus.doglovers.dogs.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedSelectionScreen(
    viewModel: BreedSelectionViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.handleScreenEvents(BreedSelectionEvent.loadBreeds)
    }



    Scaffold() { innerPadding ->
        Image(
            painter = painterResource(R.drawable.app_background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        BreedSelectionScreenContent(
            uiState = uiState,
            onBreedLoad = { viewModel.handleScreenEvents(BreedSelectionEvent.loadBreeds) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BreedSelectionScreenContent(
    uiState: BreedSelectionUIState,
    onBreedLoad: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )
            Icon(
                imageVector = ImageVector.vectorResource(com.matheus.doglovers.dogs.presentation.R.drawable.ic_envelope),
                tint = Color.White,
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp)
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
            shape = RoundedCornerShape(32.dp),
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
                            Button(onClick = { onBreedLoad.invoke() }) {
                                Text(stringResource(id = R.string.try_again))
                            }
                        }
                    }


                    uiState.breeds != null -> {
                        Column(modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                            .fillMaxSize()) {
                            Text(
                                stringResource(com.matheus.doglovers.dogs.presentation.R.string.dog_selection_screen_breed_label),
                                color = Color.Black
                            )
                            BreedSelectionDropDown(
                                uiState.breeds!!,
                                onSelected = {},
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .fillMaxWidth()
                            )

                            /*SubcomposeAsyncImage(
                                model = pokemonSummary.image,
                                contentDescription = pokemonSummary.name,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .clip(CircleShape.copy(all = CornerSize(16.dp)))
                                    .heightIn(min = 150.dp)
                            )*/
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
    onSelected: (Breed) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(breeds[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        TextField(
            value = selectedOption.name,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth(),
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
                    text = { Text(selectionOption.name) },
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
                        override val name: String
                            get() = "Breed 1"

                    },
                    object : Breed {
                        override val name: String
                            get() = "Breed 2"

                    },
                ),
            ),
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
                    override val name: String
                        get() = "Breed 1"

                },
                object : Breed {
                    override val name: String
                        get() = "Breed 2"

                },
            ),
            onSelected = {},
            modifier = Modifier
        )
    }
}