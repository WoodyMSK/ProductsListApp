package com.example.productslistapp.ui.product_list.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productslistapp.R
import com.example.productslistapp.domain.model.Product
import com.example.productslistapp.domain.parseAndFormatDate

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductListItems(
    productList: List<Product>,
    onSearch: (text: String) -> Unit,
    onDelete: (id: Int) -> Unit,
    onChangeAmount: (id: Int, amount: Int) -> Unit,
) {

    val searchText = remember { mutableStateOf("") }
    var openRemoveDialog by remember { mutableStateOf(false) }
    var openChangeAmountDialog by remember { mutableStateOf(false) }
    var itemId by remember { mutableIntStateOf(0) }
    var amount by remember { mutableIntStateOf(0) }

    if (openRemoveDialog) {
        RemoveItemDialog(
            onYesClick = {
                onDelete(itemId)
                openRemoveDialog = false
            },
            onNoClick = { openRemoveDialog = false }
        )
    }

    if (openChangeAmountDialog) {
        ChangeAmountDiaLog(
            amount = amount,
            onYesClick = {
                onChangeAmount(itemId, amount)
                openChangeAmountDialog = false
            },
            onNoClick = {
                openChangeAmountDialog = false
            },
            onIncClick = {
                amount++
            },
            onDecClick = {
                if (amount > 0) {
                    amount--
                } else {
                }
            }
        )
    }

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(0.dp),
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                query = searchText.value,
                onQueryChange = { text ->
                    searchText.value = text
                    onSearch(searchText.value)
                },
                onSearch = { text -> onSearch(text) },
                placeholder = {
                    Text(stringResource(R.string.product_search))
                },
                active = false,
                onActiveChange = { },
                shadowElevation = 4.dp,
                trailingIcon = {
                    if (searchText.value != "") {
                        IconButton(
                            onClick = {
                                searchText.value = ""
                                onSearch(searchText.value)
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = stringResource(R.string.empty_text),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                },
            ) { }
        }

        if (productList.isNotEmpty()) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                items(productList) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        ),
                    ) {

                        Column(
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = item.name,
                                    fontSize = 18.sp,
                                )
                                Row {
                                    IconButton(
                                        onClick = {
                                            amount = item.amount
                                            itemId = item.id
                                            openChangeAmountDialog = true
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Create,
                                            tint = MaterialTheme.colorScheme.primary,
                                            contentDescription = "",
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            itemId = item.id
                                            openRemoveDialog = true
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            tint = MaterialTheme.colorScheme.error,
                                            contentDescription = "",
                                        )
                                    }
                                }
                            }
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                item.tags.forEach { Tag(it) }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(top = 8.dp),
                                ) {
                                    Text(
                                        text = stringResource(R.string.in_stock),
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = item.amount.toString(),
                                        fontSize = 14.sp,
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(top = 8.dp),
                                ) {
                                    Text(
                                        text = stringResource(R.string.add_date),
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = parseAndFormatDate(item.time),
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                    }
                }


            }
        }


    }
}

@Composable
fun Tag(tag: String) {
    AssistChip(
        modifier = Modifier.padding(end = 4.dp),
        onClick = { },
        label = { Text(tag) },
    )
}