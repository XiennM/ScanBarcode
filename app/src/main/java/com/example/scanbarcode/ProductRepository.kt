package com.example.scanbarcode

import android.content.Context
import android.util.Log
import java.util.concurrent.Executors

private const val DATABASE_NAME = "product-database"
private const val TAG = "ProductRepository"

class ProductRepository private constructor(context: Context) {

    private val executor = Executors.newSingleThreadExecutor()

    fun addProduct(product: Product){

        val info = ""

        Log.d(TAG, "Get info $info")
    }
}