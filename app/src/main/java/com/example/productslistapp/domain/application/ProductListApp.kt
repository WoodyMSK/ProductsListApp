package com.example.productslistapp.domain.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProductListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        private lateinit var INSTANCE: ProductListApp
    }
}