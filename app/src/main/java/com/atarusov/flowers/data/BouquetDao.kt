package com.atarusov.flowers.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.model.Order
import com.atarusov.flowers.model.OrderBouquetCrossRef
import com.atarusov.flowers.model.OrderWithBouquets

@Dao
interface BouquetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBouquet(bouquet: Bouquet)

    @Update
    suspend fun updateBouquet(bouquet: Bouquet)

    @Query("SELECT * FROM bouquet_table ORDER BY bouquetId ASC")
    fun readAllData(): LiveData<List<Bouquet>>

    @Query("SELECT * FROM bouquet_table WHERE inCart = 1 ORDER BY bouquetId ASC")
    fun getBouquetsInCart(): LiveData<List<Bouquet>>

    @Transaction
    @Query("SELECT * FROM `order`")
    fun getOrderWithBouquets(): LiveData<List<OrderWithBouquets>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(order: Order)

    @Delete
    suspend fun removeOrder(order: Order)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrderBouquetRelation(orderBouquetCrossRef: OrderBouquetCrossRef)

    @Query("DELETE FROM orderbouquetcrossref WHERE orderId= :orderId")
    suspend fun removeAllRelationsToOrder(orderId: Int)

}