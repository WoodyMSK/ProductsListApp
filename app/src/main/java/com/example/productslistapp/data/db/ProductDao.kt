package com.example.productslistapp.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_list")
    suspend fun getProductList(): List<ProductEntity>

    @Query("DELETE FROM product_list WHERE id = :id")
    suspend fun delete(id: Int)
}