package com.matheus.doglovers.dogs.presentation.breedSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.usecases.GetRandomDogUseCase
import com.matheus.doglovers.dogs.domain.usecases.ListBreedsUseCase
import com.matheus.doglovers.dogs.domain.usecases.RemoveFavoriteDogUseCase
import com.matheus.doglovers.dogs.domain.usecases.SaveFavoriteDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedSelectionViewModel @Inject constructor(
    private val listBreedsUseCase: ListBreedsUseCase,
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val saveFavoriteDogUseCase: SaveFavoriteDogUseCase,
    private val removeFavoriteDogUseCase: RemoveFavoriteDogUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<BreedSelectionUIState> = MutableStateFlow(BreedSelectionUIState())
    val uiState = _uiState.asStateFlow()

    fun handleScreenEvents(event: BreedSelectionEvent) {
        when (event) {
            BreedSelectionEvent.LoadAllBreeds -> loadBreeds()
            is BreedSelectionEvent.LoadRandomDogImage -> {
                _uiState.value = _uiState.value.copy(selectedBreed = event.breed)
                loadRandomDogImage(event.breed)
            }

            BreedSelectionEvent.ShuffleImageForSelectedBreed -> _uiState.value.selectedBreed?.let { loadRandomDogImage(it) }
            BreedSelectionEvent.FavoriteOrUnfavoriteCurrentDog -> favOrUnfavCurrentDog()
            is BreedSelectionEvent.SetUser -> _uiState.value = _uiState.value.copy(user = event.user)
        }
    }

    private fun loadBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            listBreedsUseCase.invoke().collect {
                when (it) {
                    is Resource.Error -> _uiState.value =
                        _uiState.value.copy(loading = false, errorMessage = "Failed to load")

                    Resource.Loading -> _uiState.value = _uiState.value.copy(loading = true)
                    is Resource.Success -> _uiState.value = _uiState.value.copy(loading = false, breeds = it.data)
                }
            }
        }
    }

    private fun loadRandomDogImage(breed: Breed) {
        viewModelScope.launch(Dispatchers.IO) {
            getRandomDogUseCase.invoke(breed).collect {
                when (it) {
                    is Resource.Error -> _uiState.value =
                        _uiState.value.copy(errorMessage = "Failed to load")

                    Resource.Loading -> _uiState.value = _uiState.value.copy(loading = true, currentDog = null)
                    is Resource.Success -> _uiState.value = _uiState.value.copy(loading = false, currentDog = it.data)
                }
            }
        }
    }

    private fun favOrUnfavCurrentDog() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = uiState.value.user?.email ?: return@launch
            uiState.value.currentDog?.let {
               val result = if(!it.isFavorite) {
                    saveFavoriteDogUseCase.invoke(it, email).firstOrNull()
                } else {
                    removeFavoriteDogUseCase.invoke(it, email).firstOrNull()
                }

                if(result is Resource.Success) {
                    _uiState.value = _uiState.value.copy(currentDog = result.data)
                }

            }
        }
    }
}
