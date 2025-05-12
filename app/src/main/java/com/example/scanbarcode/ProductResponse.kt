package com.example.scanbarcode

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("product") val product: Product?
)