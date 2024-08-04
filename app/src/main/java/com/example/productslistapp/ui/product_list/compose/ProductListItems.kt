package com.example.productslistapp.ui.product_list.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productslistapp.R
import com.example.productslistapp.domain.model.Product

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductListItems(
    productList: List<Product>,
    onSearch: (text: String) -> Unit,
    onDelete: (id: Int) -> Unit,
) {

    val searchText = remember { mutableStateOf("") }
    var openDialog by remember { mutableStateOf(false) }
    var itemId by remember { mutableIntStateOf(0) }

    if (openDialog) {
        RemoveItemDialog(
            onYesClick = {
                onDelete(itemId)
                openDialog = false
            },
            onNoClick = { openDialog = false }
        )
    }

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(0.dp),
    ) {

        Column(Modifier.fillMaxWidth()) {
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
                                contentDescription = "",
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
//                .background(MaterialTheme.colorScheme.background),
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
                                        onClick = { }
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
                                            openDialog = true
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
                                        text = "На складе",
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = item.amount.toString(),
                                        fontSize = 14.sp,
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(top = 8.dp),
                                ) {
                                    Text(
                                        text = "Дата добавления",
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = item.time,
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