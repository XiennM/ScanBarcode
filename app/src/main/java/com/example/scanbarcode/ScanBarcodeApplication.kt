package com.example.scanbarcode

import android.app.Application


class ScanBarcodeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ProductRepository.initialize(this)
    }
}