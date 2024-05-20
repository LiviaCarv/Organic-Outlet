package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.R
import com.project.organicoutlet.database.ProductDao
import com.project.organicoutlet.database.ProductDatabase
import com.project.organicoutlet.databinding.ActivityProductsListBinding
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsListBinding
    private lateinit var productDao: ProductDao
    private val adapter = ProductsListAdapter { product ->
        openDetailsActivity(product.productId)
    }

    private fun openDetailsActivity(productId: Long) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("productId", productId)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list)
        val database = ProductDatabase.getInstance(this)
        productDao = database.productDao()
        bindRecyclerViewAdapter()
        fabListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
                R.id.filter_asc -> {
                    adapter.update(productDao.getProductsOrdAsc())
                    true
                }

                R.id.filter_desc -> {
                    adapter.update(productDao.getProductsOrdDesc())
                    true
                }

                else -> {
                    adapter.update(productDao.getAllProducts())
                    super.onOptionsItemSelected(item)
                    true
                }
            }
    }

    override fun onResume() {
        super.onResume()
        adapter.update(productDao.getAllProducts())
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