package com.project.organicoutlet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.databinding.ActivityMainBinding
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.productsList.adapter = ProductsListAdapter()
    }
}