package com.example.productslistapp.domain

import com.example.productslistapp.domain.model.Product

interface ProductListRepository {

    suspend fun getProductList(): List<Product>
    suspend fun removeItem(id: Int)
    suspend fun changeAmount(id: Int, amount: Int)
    suspend fun findByNameContaining(substring: String): List<Product>
}