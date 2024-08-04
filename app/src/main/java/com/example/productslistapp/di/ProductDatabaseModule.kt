package com.example.productslistapp.di

import android.content.Context
import androidx.room.Room
import com.example.productslistapp.data.db.ProductDao
import com.example.productslistapp.data.db.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProductDatabaseModule {

    @Singleton
    @Provides
    fun provideProductDatabase(
        @ApplicationContext
        context: Context
    ): ProductDatabase = Room.databaseBuilder(context, ProductDatabase::class.java, "product.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideProductDao(productDb: ProductDatabase): ProductDao = productDb.productDao()
}