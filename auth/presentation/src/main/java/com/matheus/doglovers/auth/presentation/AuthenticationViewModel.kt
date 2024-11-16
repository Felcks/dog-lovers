package com.matheus.doglovers.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(): ViewModel() {

    private val _uiState: MutableStateFlow<AuthenticationScreenUIState> = MutableStateFlow(AuthenticationScreenUIState())
    val uiState = _uiState.asStateFlow()

    private lateinit var auth: FirebaseAuth

    fun handleScreenEvents(event: AuthenticationScreenEvent) {
        when (event) {
            AuthenticationScreenEvent.CheckAuthState -> checkAuthState()
            is AuthenticationScreenEvent.SigninUser -> signinUser(event.email, event.password)
        }
    }

    private fun checkAuthState() {
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            _uiState.value = _uiState.value.copy(user = currentUser)
        }
    }

    private fun signinUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    _uiState.value = _uiState.value.copy(user = user)
                } else {
                    Log.w("script2", "signInWithEmail:failure", task.exception)
                    //updateUI(null)
                }
            }
    }
}