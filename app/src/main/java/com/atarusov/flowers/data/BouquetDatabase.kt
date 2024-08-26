package com.atarusov.flowers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.model.Order
import com.atarusov.flowers.model.OrderBouquetCrossRef

@Database(entities = [Bouquet::class, Order::class, OrderBouquetCrossRef::class], version = 1, exportSchema = false)
abstract class BouquetDatabase : RoomDatabase() {

    abstract fun bouquetDao(): BouquetDao

    companion object {
        @Volatile
        private var INSTANCE: BouquetDatabase? = null

        fun getDatabase(context: Context): BouquetDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BouquetDatabase::class.java,
                    "bouquet_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}