package com.example.productslistapp.ui.model

import com.example.productslistapp.domain.model.Product

sealed class Events {

    data class ShowProductList(val products: List<Product>) : Events()
    data object LoadingState : Events()
}