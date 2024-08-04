package com.example.productslistapp.domain.interactor

import com.example.productslistapp.domain.ProductListRepository
import com.example.productslistapp.domain.model.Product
import javax.inject.Inject

class ProductListInteractor @Inject constructor(
    private val productListRepository: ProductListRepository,
) {

    suspend fun getProductList(): List<Product> =
        productListRepository.getProductList()

    suspend fun removeItem(id: Int) =
        productListRepository.removeItem(id)

    suspend fun changeAmount(id: Int, amount: Int) =
        productListRepository.changeAmount(id, amount)

    suspend fun findByNameContaining(substring: String): List<Product> =
        productListRepository.findByNameContaining(substring)
}