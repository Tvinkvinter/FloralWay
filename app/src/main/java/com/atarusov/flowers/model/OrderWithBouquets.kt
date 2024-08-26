package com.atarusov.flowers.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OrderWithBouquets(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "bouquetId",
        associateBy = Junction(OrderBouquetCrossRef::class)
    )
    val bouquets: List<Bouquet>
)