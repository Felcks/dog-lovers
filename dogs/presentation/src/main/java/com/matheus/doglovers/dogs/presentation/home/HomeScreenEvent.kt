package com.matheus.doglovers.dogs.presentation.home

sealed class HomeScreenEvent {
    data object CheckAuthState : HomeScreenEvent()
    data object Signout : HomeScreenEvent()
}