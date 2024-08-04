package com.example.productslistapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productslistapp.domain.interactor.ProductListInteractor
import com.example.productslistapp.ui.model.Events
import com.example.productslistapp.ui.model.Events.ShowProductList
import com.example.productslistapp.ui.model.Events.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListIterator: ProductListInteractor,
) : ViewModel() {

    private val _products: MutableStateFlow<Events> = MutableStateFlow(LoadingState)
    val products: StateFlow<Events>
        get() = _products

    init {
        getProductList()
    }

    private fun getProductList() =
        viewModelScope.launch {
            val productList = productListIterator.getProductList()
            _products.tryEmit(ShowProductList(productList))
        }
}