package com.example.scanbarcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

private const val TAG = "Product VM"

class ProductViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://world.openfoodfacts.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(OpenFoodFactsApi::class.java)

    private val productRepository = ProductRepository.get()
    private val _product = MutableLiveData<Product?>()
    private val productIdLiveData = MutableLiveData<UUID>()

    val productItem: LiveData<Product> get() = productIdLiveData.switchMap {
        productId ->
            productRepository.getProduct(productId)
    }

    fun loadProduct(productId: UUID) {
        productIdLiveData.value = productId
    }

    val product: LiveData<Product?> get() = _product

    fun saveProduct(barcode: String){
        viewModelScope.launch {
            try {
                val response = api.getProduct(barcode, language = "ru")
                val product = response.body()?.product
                if (response.isSuccessful && product != null) {
                    val newProduct = product.toEntity()
                    productRepository.addProduct(newProduct)
                    _product.postValue(newProduct)
                    Log.d(TAG, "posted product")
                } else {
                }
            } catch (e: Exception) {
                Log.d("ProductViewModel", "Ошибка запроса: ${e.message}")
            }
        }
    }
}