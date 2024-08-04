package com.example.productslistapp.ui.product_list.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productslistapp.domain.model.Product

//@Preview
@Composable
fun ProductListItems(
    productList: List<Product>
) {

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(0.dp),
    ) {

        TextFieldExample()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp),
                ),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xff757575),
                    text = "Search a Tag or Description",
                )
            }
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.background),
        ) {
            items(5) {
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
                                text = "iPhone 13",
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
                                    onClick = { }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        tint = MaterialTheme.colorScheme.error,
                                        contentDescription = "",
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Tag()
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
                                    text = "30",
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
                                    text = "01.10.2021",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldExample() {
    var textInput by remember { mutableStateOf("") }
    OutlinedTextField(
//        trailingIcon = Icon(Icons.Default.Search, contentDescription = ""),
//        leadingIcon = Icon(Icons.Default.Search, contentDescription = ""),
        value = textInput, onValueChange = { textInput = it },
        label = { Text("Username") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Blue,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tag() {
    AssistChip(
        onClick = { },
        label = { Text("Assist Chip") },
    )
}