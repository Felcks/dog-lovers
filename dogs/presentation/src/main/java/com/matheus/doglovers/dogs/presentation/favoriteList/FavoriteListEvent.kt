package com.matheus.doglovers.dogs.presentation.favoriteList

sealed class FavoriteListEvent {
   data object LoadFavoriteDogs : FavoriteListEvent()
}