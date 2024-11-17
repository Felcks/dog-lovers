package com.matheus.doglovers.dogs.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matheus.doglovers.dogs.persistence.dao.FavoriteDogsDao
import com.matheus.doglovers.dogs.persistence.models.BreedEntity
import com.matheus.doglovers.dogs.persistence.models.DogEntity

@Database(entities = [DogEntity::class, BreedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDogsDao(): FavoriteDogsDao
}
