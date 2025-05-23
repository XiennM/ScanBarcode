package com.example.scanbarcode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity
data class Product(
    @SerializedName("product_name_ru")
    val nameRu: String?,

    @SerializedName("product_name")
    val nameEn: String?,

    @SerializedName("brands")
    val brands: String?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("ingredients_text_ru")
    val ingredientsRu: String?,

    @SerializedName("ingredients_text")
    val ingredientsEn: String?,

    @SerializedName("quantity_per_unit")
    val quantity: String?
) {
    // Название продукта с фолбэком
    val displayName: String
        get() = nameRu ?: nameEn ?: "Название неизвестно"

    // Ингредиенты с фолбэком
    val displayIngredients: String
        get() = ingredientsRu ?: ingredientsEn ?: "Нет информации об ингредиентах"

    // Бренд с фолбэком
    val displayBrand: String
        get() = brands ?: "Бренд не указан"

    // URL картинки, если null — можно подставить заглушку (если Glide позволяет)
    val displayImageUrl: String
        get() = imageUrl ?: ""

    // Количество (масса/объём)
    val displayQuantity: String
        get() = quantity ?: "Количество не указано"
}