package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.R
import com.project.organicoutlet.dao.ProductsDao
import com.project.organicoutlet.databinding.ActivityProductsListBinding
import com.project.organicoutlet.model.Product
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsListBinding
    private val dao: ProductsDao = ProductsDao()
    private val adapter = ProductsListAdapter(dao.getAllProducts(), { product ->
        openDetailsActivity(product)
    })

    private fun openDetailsActivity(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list)
        bindRecyclerViewAdapter()
        fabListener()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.getAllProducts())
    }

    private fun bindRecyclerViewAdapter() {
        binding.productsList.adapter = adapter
    }

    private fun fabListener() {
        binding.fabAddProduct.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }
}