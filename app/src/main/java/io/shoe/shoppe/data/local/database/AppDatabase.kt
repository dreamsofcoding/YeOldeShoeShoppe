package io.shoe.shoppe.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShoeShoppe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoeShoppeDao(): ShoeShoppeDao
}
