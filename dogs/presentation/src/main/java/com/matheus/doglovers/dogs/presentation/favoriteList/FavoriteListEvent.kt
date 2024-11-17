package com.matheus.doglovers.dogs.presentation.favoriteList

import com.google.firebase.auth.FirebaseUser

sealed class FavoriteListEvent {
   data class SetUser(val user: FirebaseUser): FavoriteListEvent()
   data object LoadFavoriteDogs : FavoriteListEvent()
}