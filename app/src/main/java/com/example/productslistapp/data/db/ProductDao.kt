package com.example.productslistapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_list")
    suspend fun getProductList(): List<ProductEntity>

    @Query("DELETE FROM product_list WHERE id = :id")
    suspend fun delete(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: List<ProductEntity>)

    @Query(
        """
        UPDATE product_list SET
        amount = :amount
        WHERE id = :id
    """
    )
    suspend fun changeAmount(id: Int, amount: Int)

    @Query("SELECT * FROM product_list WHERE name LIKE '%' || :substring || '%'")
    suspend fun findByNameContaining(substring: String): List<ProductEntity>
}