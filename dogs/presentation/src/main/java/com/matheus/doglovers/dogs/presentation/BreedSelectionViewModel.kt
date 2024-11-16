package com.matheus.doglovers.dogs.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.models.Breed
import com.matheus.doglovers.dogs.domain.usecases.GetRandomDogUseCase
import com.matheus.doglovers.dogs.domain.usecases.ListBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedSelectionViewModel @Inject constructor(
    private val listBreedsUseCase: ListBreedsUseCase,
    private val getRandomDogUseCase: GetRandomDogUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<BreedSelectionUIState> = MutableStateFlow(BreedSelectionUIState())
    val uiState = _uiState.asStateFlow()

    private lateinit var auth : FirebaseAuth

    fun handleScreenEvents(event: BreedSelectionEvent) {
        when (event) {
            BreedSelectionEvent.CheckAuthState -> checkAuthState()
            BreedSelectionEvent.LoadAllBreeds -> loadBreeds()
            is BreedSelectionEvent.LoadRandomDogImage -> {
                _uiState.value = _uiState.value.copy(selectedBreed = event.breed)
                loadRandomDogImage(event.breed)
            }

            BreedSelectionEvent.ShuffleImageForSelecteBreed -> _uiState.value.selectedBreed?.let { loadRandomDogImage(it) }
            BreedSelectionEvent.Signout -> signout()
        }
    }

    private fun checkAuthState() {
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            _uiState.value = _uiState.value.copy(user = currentUser)
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

    private fun signout() {
        val auth = Firebase.auth
        auth.signOut()
        _uiState.value = _uiState.value.copy(user = null)
    }
}
