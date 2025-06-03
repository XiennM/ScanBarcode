package com.example.scanbarcode

import androidx.lifecycle.ViewModel

class ProductListViewModel : ViewModel() {

    private val productRepository = ProductRepository.get()
    val productListLiveData = productRepository.getProducts()
}