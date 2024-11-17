package com.matheus.doglovers.dogs.presentation.home

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.matheus.doglovers.dogs.presentation.breedSelection.BreedSelectionEvent
import com.matheus.doglovers.dogs.presentation.breedSelection.BreedSelectionUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel() {
    private lateinit var auth : FirebaseAuth

    private val _uiState: MutableStateFlow<HomeScreenUIState> = MutableStateFlow(HomeScreenUIState())
    val uiState = _uiState.asStateFlow()

    fun handleScreenEvents(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.CheckAuthState -> checkAuthState()
            HomeScreenEvent.Signout -> signout()
        }
    }

    private fun checkAuthState() {
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            _uiState.value = _uiState.value.copy(user = currentUser)
        }
    }

    private fun signout() {
        val auth = Firebase.auth
        auth.signOut()
        _uiState.value = _uiState.value.copy(user = null)
    }
}