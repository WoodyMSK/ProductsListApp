package com.example.productslistapp.data

import com.example.productslistapp.data.db.ProductDao
import com.example.productslistapp.data.db.mapProductsFromEntity
import com.example.productslistapp.domain.ProductListRepository
import com.example.productslistapp.domain.model.Product
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
) : ProductListRepository {

    override suspend fun getProductList(): List<Product> {
        val productList = productDao.getProductList()
        return mapProductsFromEntity(productList)
    }

    override suspend fun removeItem(id: Int) = productDao.delete(id)
    override suspend fun changeAmount(id: Int, amount: Int) = productDao.changeAmount(
        id = id,
        amount = amount,
    )

    override suspend fun findByNameContaining(substring: String): List<Product> {
        val productList = productDao.findByNameContaining(substring)
        return mapProductsFromEntity(productList)
    }
}