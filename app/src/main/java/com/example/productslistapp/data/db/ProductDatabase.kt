package com.example.productslistapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ProductEntity::class],
    version = 1,
)
@TypeConverters(DataTypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.productDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(productDao: ProductDao) {

            val initialData = listOf(
                ProductEntity(
                    id = 1,
                    name = "iPhone 13",
                    time = "1633046400000",
                    tags = listOf("Телефон", "Новый", "Распродажа"),
                    amount = 15,
                ),
                ProductEntity(
                    id = 2,
                    name = "Samsung Galaxy S21",
                    time = "1633132800000",
                    tags = listOf("Телефон", "Хит"),
                    amount = 30,
                ),
                ProductEntity(
                    id = 3,
                    name = "PlayStation 5",
                    time = "1633219200000",
                    tags = listOf("Игровая приставка", "Акция", "Распродажа"),
                    amount = 7,
                ),
                ProductEntity(
                    id = 4,
                    name = "LG OLED TV",
                    time = "1633305600000",
                    tags = listOf("Телевизор", "Эксклюзив", "Ограниченный"),
                    amount = 22,
                ),
                ProductEntity(
                    id = 5,
                    name = "Apple Watch Series 7",
                    time = "1633392000000",
                    tags = listOf("Часы", "Новый", "Рекомендуем"),
                    amount = 0,
                ),
                ProductEntity(
                    id = 6,
                    name = "Xiaomi Mi 11",
                    time = "1633478400000",
                    tags = listOf("Телефон", "Скидка", "Распродажа"),
                    amount = 5,
                ),
                ProductEntity(
                    id = 7,
                    name = "MacBook Air M1",
                    time = "1633564800000",
                    tags = listOf("Ноутбук", "Тренд"),
                    amount = 12,
                ),
                ProductEntity(
                    id = 8,
                    name = "Amazon Kindle Paperwhite",
                    time = "1633651200000",
                    tags = listOf("Электронная книга", "Последний шанс", "Ограниченный"),
                    amount = 18,
                ),
                ProductEntity(
                    id = 9,
                    name = "Fibit Charge 5",
                    time = "1633737600000",
                    tags = listOf(),
                    amount = 27,
                ),
                ProductEntity(
                    id = 10,
                    name = "GoPro Hero 10",
                    time = "1633824000000",
                    tags = listOf("Камера", "Эксклюзив"),
                    amount = 25,
                ),
            )

            productDao.insert(initialData)
        }
    }
}