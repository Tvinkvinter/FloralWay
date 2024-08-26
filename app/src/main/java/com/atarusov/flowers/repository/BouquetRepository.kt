package com.atarusov.flowers.repository

import androidx.lifecycle.LiveData
import com.atarusov.flowers.data.BouquetDao
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.model.Order
import com.atarusov.flowers.model.OrderBouquetCrossRef
import com.atarusov.flowers.model.OrderWithBouquets

class BouquetRepository(private val bouquetDao: BouquetDao) {

    val readAllData: LiveData<List<Bouquet>> = bouquetDao.readAllData()
    val getBouquetsInCart: LiveData<List<Bouquet>> = bouquetDao.getBouquetsInCart()
    val getOrdersWithBouquets: LiveData<List<OrderWithBouquets>> = bouquetDao.getOrderWithBouquets()

    suspend fun addBouquet(bouquet: Bouquet) {
        bouquetDao.addBouquet(bouquet)
    }

    suspend fun updateBouquet(bouquet: Bouquet) {
        bouquetDao.updateBouquet(bouquet)
    }

    suspend fun addOrder(order: Order) {
        bouquetDao.addOrder(order)
    }

    suspend fun removeOrder(order: Order) {
        bouquetDao.removeOrder(order)
    }

    suspend fun addOrderBouquetRelation(orderBouquetCrossRef: OrderBouquetCrossRef) {
        bouquetDao.addOrderBouquetRelation(orderBouquetCrossRef)
    }

    suspend fun removeAllRelationsToOrder(orderId: Int) {
        bouquetDao.removeAllRelationsToOrder(orderId)
    }

}