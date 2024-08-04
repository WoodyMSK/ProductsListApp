package com.example.productslistapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productslistapp.domain.interactor.ProductListInteractor
import com.example.productslistapp.domain.model.Product
import com.example.productslistapp.ui.model.Events
import com.example.productslistapp.ui.model.Events.ShowProductList
import com.example.productslistapp.ui.model.Events.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private var initProductList = listOf<Product>()

    fun searchProduct(text: String) = viewModelScope.launch {
        if (text.isEmpty()) {
            _products.tryEmit(ShowProductList(initProductList))
        } else {

            val searchList = initProductList.filter { product ->
                product.name.contains(text.trim(), ignoreCase = true)
            }

            _products.tryEmit(ShowProductList(searchList))
        }
    }

    fun onDeleteClick(id: Int) = viewModelScope.launch {
        val newProductList = initProductList.filterNot { product ->
            product.id == id
        }

        initProductList = newProductList
        _products.tryEmit(ShowProductList(newProductList))
    }

    private fun getProductList() {
        viewModelScope.launch {

//            val productList = productListIterator.getProductList()

            val productList = listOf(
                Product(
                    id = 1,
                    name = "iPhone 13",
                    time = "1633046400000",
                    tags = listOf("Телефон", "Новый", "Распродажа"),
                    amount = 15,
                ),
                Product(
                    id = 2,
                    name = "Samsung Galaxy S21",
                    time = "1633132800000",
                    tags = listOf("Телефон", "Хит"),
                    amount = 30,
                ),
                Product(
                    id = 3,
                    name = "PlayStation 5",
                    time = "1633219200000",
                    tags = listOf("Игровая приставка", "Акция", "Распродажа"),
                    amount = 7,
                ),
                Product(
                    id = 4,
                    name = "LG OLED TV",
                    time = "1633305600000",
                    tags = listOf("Телевизор", "Эксклюзив", "Ограниченный"),
                    amount = 22,
                ),
                Product(
                    id = 5,
                    name = "Apple Watch Series 7",
                    time = "1633392000000",
                    tags = listOf("Часы", "Новый", "Рекомендуем"),
                    amount = 0,
                ),
                Product(
                    id = 6,
                    name = "Xiaomi Mi 11",
                    time = "1633478400000",
                    tags = listOf("Телефон", "Скидка", "Распродажа"),
                    amount = 5,
                ),
                Product(
                    id = 7,
                    name = "MacBook Air M1",
                    time = "1633564800000",
                    tags = listOf("Ноутбук", "Тренд"),
                    amount = 12,
                ),
                Product(
                    id = 8,
                    name = "Amazon Kindle Paperwhite",
                    time = "1633651200000",
                    tags = listOf("Электронная книга", "Последний шанс", "Ограниченный"),
                    amount = 18,
                ),
                Product(
                    id = 9,
                    name = "Fibit Charge 5",
                    time = "1633737600000",
                    tags = listOf(),
                    amount = 27,
                ),
                Product(
                    id = 10,
                    name = "GoPro Hero 10",
                    time = "1633824000000",
                    tags = listOf("Камера", "Эксклюзив"),
                    amount = 25,
                ),
            )

            delay(1000)

            initProductList = productList

            _products.tryEmit(ShowProductList(productList))
        }
    }
}