package com.atarusov.flowers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bouquet_table")
data class Bouquet(
    @PrimaryKey(autoGenerate = true)
    val bouquetId: Int,
    val photo: Int,
    val description: String,
    val price: Int,
    val inCart: Boolean = false,
)