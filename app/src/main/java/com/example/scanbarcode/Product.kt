package com.example.scanbarcode

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

private const val TAG = "Product"

@Entity
data class Product(
    @PrimaryKey val id: UUID = UUID.randomUUID(),

    var date: Date = Date(),

    val name: String,

    val brands: String,

    val imageUrl: String?,

    val ingredients: String,

    val quantity: String,

    val categoriesTags: String,

    val allergensTags: String? = null,

    val isVegeterian: String?,

    val isVegan: String?,

    val proteins: String,

    val fats: String,

    val carbohydrates: String,

    val energy: String
)