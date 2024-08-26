package com.atarusov.flowers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey
    val orderId: Int,
    val date: Long,
    val sum: Int
)
