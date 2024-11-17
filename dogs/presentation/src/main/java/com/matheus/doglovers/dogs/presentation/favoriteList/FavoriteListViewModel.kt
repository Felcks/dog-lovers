package com.matheus.doglovers.dogs.presentation.favoriteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.domain.usecases.GetFavoriteDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val getFavoriteDogsUseCase: GetFavoriteDogsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteListUIState> = MutableStateFlow(FavoriteListUIState())
    val uiState = _uiState.asStateFlow()

    fun handleScreenEvents(event: FavoriteListEvent) {
        when (event) {
            FavoriteListEvent.LoadFavoriteDogs -> loadFavoriteDogs()
        }
    }

    private fun loadFavoriteDogs() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteDogsUseCase.invoke().collect {
                when (it) {
                    is Resource.Error -> _uiState.value =
                        _uiState.value.copy(loading = false, errorMessage = "Failed to load")

                    Resource.Loading -> _uiState.value = _uiState.value.copy(loading = true)
                    is Resource.Success -> _uiState.value = _uiState.value.copy(loading = false, dogs = it.data)
                }
            }
        }
    }
}