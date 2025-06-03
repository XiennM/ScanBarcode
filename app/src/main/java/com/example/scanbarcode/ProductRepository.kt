package com.example.scanbarcode

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.scanbarcode.database.ProductDatabase
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "product-database"

class ProductRepository private constructor(context: Context){

    private val database: ProductDatabase = Room.databaseBuilder(
        context.applicationContext,
        ProductDatabase::class.java,
        com.example.scanbarcode.DATABASE_NAME
    ).build()

    private val productDao = database.productDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getProducts(): LiveData<List<Product>> = productDao.getProducts()

    fun getProduct(id: UUID): LiveData<Product> = productDao.getProduct(id)

    fun updateProduct(product: Product) {
        executor.execute {
            productDao.updateProduct(product)
        }
    }

    fun addProduct(product: Product) {
        executor.execute {
            productDao.addProduct(product)
        }
    }

    companion object{
        private var INSTANCE: ProductRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null) {
                INSTANCE = ProductRepository(context)
            }
        }

        fun get(): ProductRepository {
            return INSTANCE ?:
            throw IllegalStateException("ProductRepository must be initialized")
        }
    }
}