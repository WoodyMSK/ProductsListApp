package com.example.productslistapp.data

import com.example.productslistapp.data.db.ProductDao
import com.example.productslistapp.data.db.mapProductsFromEntity
import com.example.productslistapp.domain.ProductListRepository
import com.example.productslistapp.domain.model.Product
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
) : ProductListRepository {

    override suspend fun getProductList(): List<Product> =
        mapProductsFromEntity(productDao.getProductList())
}