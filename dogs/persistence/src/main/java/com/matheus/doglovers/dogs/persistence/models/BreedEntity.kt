package com.matheus.doglovers.dogs.persistence.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matheus.doglovers.dogs.domain.models.Breed

@Entity(tableName = "breeds")
data class BreedEntity(
    @PrimaryKey @ColumnInfo(name = "name") override val name: String,
    @ColumnInfo(name = "specification") override val specification: String?,
) : Breed