package com.matheus.doglovers.dogs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.doglovers.core.domain.wrapper.Resource
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
) : ViewModel() {

    private val _uiState: MutableStateFlow<BreedSelectionUIState> = MutableStateFlow(BreedSelectionUIState())
    val uiState = _uiState.asStateFlow()

    fun handleScreenEvents(event: BreedSelectionEvent) {
        when (event) {
            BreedSelectionEvent.loadBreeds -> loadBreeds()
        }
    }

    private fun loadBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            listBreedsUseCase.invoke().collect {
                when(it) {
                    is Resource.Error -> _uiState.value = _uiState.value.copy(loading = false, errorMessage = "Failed to load")
                    Resource.Loading -> _uiState.value = _uiState.value.copy(loading = true)
                    is Resource.Success -> _uiState.value = _uiState.value.copy(loading = false, breeds = it.data)
                }
            }
        }
    }

}
