package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.project.organicoutlet.R
import com.project.organicoutlet.database.AppDatabase
import com.project.organicoutlet.databinding.ActivityProductsListBinding
import com.project.organicoutlet.preferences.dataStore
import com.project.organicoutlet.preferences.userPreferences
import com.project.organicoutlet.ui.extensions.changeActivity
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter
import kotlinx.coroutines.launch

class ProductsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsListBinding
    private val adapter = ProductsListAdapter { product ->
        openDetailsActivity(product.productId)
    }
    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }
    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list)
        bindRecyclerViewAdapter()
        fabListener()

        lifecycleScope.launch {
            launch {
                productDao.getAllProducts().collect { products ->
                    adapter.update(products)
                }
            }
            dataStore.data.collect { preferences ->
                preferences[userPreferences]?.let {
                    launch { userDao.searchUserById(it).collect { } }
                } ?: openLoginActivity()
            }
        }

    }

    private fun openLoginActivity() {
        changeActivity(LoginActivity::class.java)
        finish()
    }

    private fun openDetailsActivity(productId: Long) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("productId", productId)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_asc -> {
                lifecycleScope.launch {
                    productDao.getProductsOrdAsc().collect { products ->
                        adapter.update(products)
                    }
                }
                return true
            }

            R.id.filter_desc -> {
                lifecycleScope.launch {
                    productDao.getProductsOrdDesc().collect { products ->
                        adapter.update(products)
                    }
                }
                return true
            }

            R.id.filter_creat -> {
                lifecycleScope.launch {
                    productDao.getAllProducts().collect { products ->
                        adapter.update(products)
                    }
                }
                return true
            }

            R.id.logout -> {
                lifecycleScope.launch {
                    dataStore.edit { preferences ->
                        preferences.remove(userPreferences)
                    }
                }
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
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