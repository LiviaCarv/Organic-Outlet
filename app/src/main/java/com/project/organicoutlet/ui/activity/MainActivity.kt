package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.R
import com.project.organicoutlet.dao.ProductsDao
import com.project.organicoutlet.databinding.ActivityMainBinding
import com.project.organicoutlet.databinding.ProductItemBinding
import com.project.organicoutlet.model.Product
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    lateinit var dao: ProductsDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = ProductsDao()

    }

    override fun onResume() {
        super.onResume()
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.fabAddProduct.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
        binding.productsList.adapter = ProductsListAdapter(dao.getAllProducts())
    }
}