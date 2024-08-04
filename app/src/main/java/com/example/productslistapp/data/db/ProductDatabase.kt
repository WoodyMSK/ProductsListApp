package com.example.productslistapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProductEntity::class],
    version = 1,
)
@TypeConverters(DataTypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}