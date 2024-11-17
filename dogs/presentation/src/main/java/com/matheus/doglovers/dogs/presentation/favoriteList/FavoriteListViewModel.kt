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
            is FavoriteListEvent.SetUser -> _uiState.value = _uiState.value.copy(user = event.user)
        }
    }

    private fun loadFavoriteDogs() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.value.user?.email?.let {
                getFavoriteDogsUseCase.invoke(it).collect { response ->
                    when (response) {
                        is Resource.Error -> _uiState.value =
                            _uiState.value.copy(loading = false, errorMessage = "Failed to load")

                        Resource.Loading -> _uiState.value = _uiState.value.copy(loading = true)
                        is Resource.Success -> _uiState.value = _uiState.value.copy(loading = false, dogs = response.data)
                    }
                }
            }
        }
    }
}