package com.atarusov.flowers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.atarusov.flowers.data.BouquetDatabase
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.model.Order
import com.atarusov.flowers.model.OrderBouquetCrossRef
import com.atarusov.flowers.model.OrderWithBouquets
import com.atarusov.flowers.repository.BouquetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BouquetViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Bouquet>>
    val bouquetsInCart: LiveData<List<Bouquet>>
    val ordersWithBouquets: LiveData<List<OrderWithBouquets>>

    private val repository: BouquetRepository

    init {
        val bouquetDao = BouquetDatabase.getDatabase(application).bouquetDao()
        repository = BouquetRepository(bouquetDao)
        readAllData = repository.readAllData
        bouquetsInCart = repository.getBouquetsInCart
        ordersWithBouquets = repository.getOrdersWithBouquets
    }

    fun addBouquet(bouquet: Bouquet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBouquet(bouquet)
        }
    }

    fun putBouquetToCart(bouquet: Bouquet) {
        val newBouquet = Bouquet(
            bouquet.bouquetId,
            bouquet.photo,
            bouquet.description,
            bouquet.price,
            inCart = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBouquet(newBouquet)
        }
    }

    fun removeBouquetFromCart(bouquet: Bouquet) {
        val newBouquet = Bouquet(
            bouquet.bouquetId,
            bouquet.photo,
            bouquet.description,
            bouquet.price,
            inCart = false
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBouquet(newBouquet)
        }
    }

    fun addOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrder(order)
        }
    }

    fun removeOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeOrder(order)
        }
    }

    fun addBouquetToOrder(orderBouquetCrossRef: OrderBouquetCrossRef) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrderBouquetRelation(orderBouquetCrossRef)
        }
    }

    fun removeAllRelationsToOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeAllRelationsToOrder(order.orderId)
        }
    }

    fun makeOrder(date: Long) {
        var id = 1
        if (ordersWithBouquets.value!!.isNotEmpty())
            id = ordersWithBouquets.value!!.last().order.orderId + 1

        val order = Order(id, date, (bouquetsInCart.value as List).sumOf { it.price })

        addOrder(order)

        for (bouquet in bouquetsInCart.value!!) {
            addBouquetToOrder(OrderBouquetCrossRef(order.orderId, bouquet.bouquetId))
            removeBouquetFromCart(bouquet)
        }

    }

    fun cancelOrder(order: Order) {
        removeOrder(order)
        removeAllRelationsToOrder(order)
    }


}