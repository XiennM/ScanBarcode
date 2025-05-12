package com.example.scanbarcode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity
data class Product(
                   @SerializedName("product_name") val name: String?,
                   @SerializedName("brands") val brands: String?,
                   @SerializedName("image_url") val imageUrl: String?,
                   @SerializedName("ingredients_text") val ingredients: String?) {
}