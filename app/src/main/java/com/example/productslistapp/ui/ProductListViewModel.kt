package com.example.productslistapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productslistapp.domain.interactor.ProductListInteractor
import com.example.productslistapp.ui.model.Events
import com.example.productslistapp.ui.model.Events.LoadingState
import com.example.productslistapp.ui.model.Events.ShowProductList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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
        updateData()
    }

    fun findByNameContaining(substring: String) = viewModelScope.launch {
        _products.tryEmit(ShowProductList(productListIterator.findByNameContaining(substring)))
    }

    fun onDeleteClick(id: Int) = viewModelScope.launch {
        productListIterator.removeItem(id)
        updateData()
    }

    fun onChangeAmount(id: Int, amount: Int) = viewModelScope.launch {
        productListIterator.changeAmount(id, amount)
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            var productList = getProductList().await()
            if (productList.isEmpty()) {
                productList = getProductList().await()
            }

            _products.tryEmit(ShowProductList(productList))
        }
    }

    private fun getProductList() = viewModelScope.async {
        return@async productListIterator.getProductList()
    }
}