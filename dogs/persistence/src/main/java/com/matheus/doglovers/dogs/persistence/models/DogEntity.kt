package com.matheus.doglovers.dogs.persistence.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matheus.doglovers.dogs.domain.models.Dog

@Entity(tableName = "favorite_dogs")
data class DogEntity(
    @PrimaryKey @ColumnInfo(name = "imageUrl") override val imageUrl: String,
    @Embedded override val breed: BreedEntity,
) : Dog