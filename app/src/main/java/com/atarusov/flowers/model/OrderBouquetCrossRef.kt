package com.atarusov.flowers.model

import androidx.room.Entity

@Entity(primaryKeys = ["orderId", "bouquetId"])
data class OrderBouquetCrossRef(
    val orderId: Int,
    val bouquetId: Int
)