package com.matheus.doglovers.dogs.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matheus.doglovers.dogs.persistence.models.DogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDogsDao {
    @Query("SELECT * FROM favorite_dogs")
    fun getAll(): Flow<List<DogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dogs: DogEntity) : Unit
}