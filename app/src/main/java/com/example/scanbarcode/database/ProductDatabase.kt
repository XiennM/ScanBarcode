package com.example.scanbarcode.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scanbarcode.Product

@Database(entities = [Product::class ], version = 1, exportSchema = false)
@TypeConverters(ProductTypeConverters::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao


}