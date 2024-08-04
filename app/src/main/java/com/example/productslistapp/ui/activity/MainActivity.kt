package com.example.productslistapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productslistapp.databinding.ActivityMainBinding
import com.example.productslistapp.ui.ProductListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
            .also { setContentView(it.root) }

        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, ProductListFragment())
            .commit()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }
}