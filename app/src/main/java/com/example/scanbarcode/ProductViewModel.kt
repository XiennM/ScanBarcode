package com.example.scanbarcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ru.openfoodfacts.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(OpenFoodFactsApi::class.java)

    private val _product = MutableLiveData<Product?>()
    val product: LiveData<Product?> get() = _product

    fun loadProduct(barcode: String) {
        viewModelScope.launch {
            try {
                val response = api.getProduct(barcode, language = "ru")
                if (response.isSuccessful) {
                    _product.postValue(response.body()?.product)
                } else {
                    _product.postValue(null)
                }
            } catch (e: Exception) {
                Log.d("ProductViewModel", "Ошибка запроса: ${e.message}")
                _product.postValue(null)
            }
        }
    }
}