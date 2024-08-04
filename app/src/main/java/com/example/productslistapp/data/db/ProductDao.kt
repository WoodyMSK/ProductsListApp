package com.example.productslistapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productslistapp.domain.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_list")
    suspend fun getProductList(): List<ProductEntity>

    @Query("DELETE FROM product_list WHERE id = :id")
    suspend fun delete(id: Int)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(item: Product)
}