package com.store.stylish.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.store.stylish.data.local.converter.StringListConverter
import com.store.stylish.data.local.dao.WishlistDao
import com.store.stylish.domain.model.Product

@Database(
    entities = [Product::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class EcoMartDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
}
