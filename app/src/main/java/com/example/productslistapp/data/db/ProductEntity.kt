package com.example.productslistapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.productslistapp.domain.model.Product

@Entity(tableName = "product_list")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: String,
    val tags: List<String>,
    val amount: Int,
)

fun mapProductsFromEntity(items: List<ProductEntity>): List<Product> =
    items.map { productEntity ->
        Product(
            id = productEntity.id,
            name = productEntity.name,
            time = productEntity.time,
            tags = productEntity.tags,
            amount = productEntity.amount,
        )
    }