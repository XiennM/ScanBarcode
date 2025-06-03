package com.example.scanbarcode.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scanbarcode.Product
import java.util.UUID

@Dao
interface ProductDao{
    @Query("SELECT * FROM product")
    fun getCrimes(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Product?>

    @Update
    fun updateCrime(product: Product)

    @Insert
    fun addCrime(product: Product)
}