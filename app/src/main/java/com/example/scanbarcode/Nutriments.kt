package com.example.scanbarcode

import com.google.gson.annotations.SerializedName

data class Nutriments(
    @SerializedName("proteins_100g") val proteins: Double?,
    @SerializedName("fat_100g") val fat: Double?,
    @SerializedName("carbohydrates_100g") val carbohydrates: Double?,
    @SerializedName("energy") val energy: Double?
)