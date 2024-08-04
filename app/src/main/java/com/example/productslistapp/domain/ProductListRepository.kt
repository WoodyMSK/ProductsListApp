package com.example.productslistapp.domain

import com.example.productslistapp.domain.model.Product

interface ProductListRepository {

    suspend fun getProductList(): List<Product>
}