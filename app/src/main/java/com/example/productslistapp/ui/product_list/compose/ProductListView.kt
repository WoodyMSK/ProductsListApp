package com.example.productslistapp.ui.product_list.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.productslistapp.domain.model.Product
import com.example.productslistapp.ui.ProductListViewModel
import com.example.productslistapp.ui.model.Events
import com.example.productslistapp.ui.model.Events.ShowProductList
import com.example.productslistapp.ui.model.Events.LoadingState

@Composable
fun ProductListView(productViewModel: ProductListViewModel) {

    val state: Events by productViewModel.products.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        when (val event = state) {
            is ShowProductList -> {
                ProductListItems(
                    productList = event.products,
                    onSearch = productViewModel::searchProduct,
                    onDelete = productViewModel::onDeleteClick,
                )
            }
            is LoadingState -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
            }
        }
    }
}