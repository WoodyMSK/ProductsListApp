package com.example.productslistapp.domain.interactor

import com.example.productslistapp.domain.ProductListRepository
import com.example.productslistapp.domain.model.Product
import javax.inject.Inject

class ProductListInteractor @Inject constructor(
    private val productListRepository: ProductListRepository,
) {

    suspend fun getProductList(): List<Product> =
        productListRepository.getProductList()
}