package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.project.organicoutlet.R
import com.project.organicoutlet.database.AppDatabase
import com.project.organicoutlet.databinding.ActivityProductsListBinding
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ProductsListActivity : UserBaseActivity() {
    private lateinit var binding: ActivityProductsListBinding
    private val adapter = ProductsListAdapter { product ->
        openDetailsActivity(product.productId)
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
            searchUserProducts("all")
        }

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
                    searchUserProducts("asc")
                }
                return true
            }

            R.id.filter_desc -> {
                lifecycleScope.launch {
                    searchUserProducts("desc")
                }
                return true
            }

            R.id.filter_creat -> {
                lifecycleScope.launch {
                    searchUserProducts("all")
                }
                return true
            }

            R.id.logout -> {
                lifecycleScope.launch {
                    userLogOut()
                }
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private suspend fun searchUserProducts(option: String) {
        currentUser.filterNotNull().collect {
            when (option) {
                "asc" -> {
                    productDao.getProductsOrdAsc(it.id).collect { products ->
                        adapter.update(products)
                    }
                }

                "desc" -> {
                    productDao.getProductsOrdDesc(it.id).collect { products ->
                        adapter.update(products)
                    }
                }

                else -> {
                    productDao.getProductsByUser(it.id).collect { products ->
                        adapter.update(products)
                    }

                }
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