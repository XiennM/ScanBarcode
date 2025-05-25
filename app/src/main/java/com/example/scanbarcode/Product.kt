package com.example.scanbarcode

import android.util.Log
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.example.scanbarcode.data.AllergenTranslations
import com.example.scanbarcode.data.CategoryTranslations

private const val TAG = "Product"

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

    @SerializedName("quantity")
    val quantity: String?,

    @SerializedName("categories_tags")
    val categoriesTags: List<String>?,

    @SerializedName("allergens_tags")
    val allergensTags: List<String>? = null,

    @SerializedName("labels_tags")
    val labelsTags: List<String>? = null,

    @SerializedName("nutriments")
    val nutriments: Nutriments?
) {
    val displayName: String
        get() = nameRu ?: nameEn ?: "Название неизвестно"

    val displayIngredients: String
        get() = ingredientsRu ?: ingredientsEn ?: "Нет информации об ингредиентах"

    val displayBrand: String
        get() = brands ?: "Бренд не указан"

    val displayQuantity: String
        get() = quantity ?: "Количество не указано"

    val displayCategories: String
        get() {
            if (categoriesTags.isNullOrEmpty()) return "Категории не указаны"
            return categoriesTags.joinToString(", ") {
                CategoryTranslations.map[it] ?: it.removePrefix("en:").replace("-", " ")
                    .replaceFirstChar { it.uppercase() }
            }
        }

    val displayAllergens: String
        get() {
            if (allergensTags.isNullOrEmpty()) return "Аллергены не указаны"
            return allergensTags.joinToString(", ") {
                AllergenTranslations.map[it] ?: it.removePrefix("en:").replace("-", " ")
                    .replaceFirstChar { c -> c.uppercase() }
            }
        }

    val isVegetarian: String?
        get() = if (labelsTags?.any { it == "en:vegetarian" || it == "en:vegetarian-products" } == true) "Продукт подходит для вегетерианцев" else null

    val isVegan: String?
        get() = if (labelsTags?.any { it == "en:vegan" || it == "en:vegan-products" } == true) "Продукт подходит для веганов" else null

    val displayProteins: String
        get() = nutriments?.proteins?.let { "$it г" } ?: "-"

    val displayFats: String
        get() = nutriments?.fat?.let { "$it г" } ?: "-"

    val displayCarbohydrates: String
        get() = nutriments?.carbohydrates?.let { "$it г" } ?: "-"

    val displayEnergy: String
        get() = nutriments?.energy?.let { "$it кДж" } ?: "-"

}