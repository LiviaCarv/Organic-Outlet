package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.R
import com.project.organicoutlet.database.Product
import com.project.organicoutlet.database.ProductDao
import com.project.organicoutlet.database.ProductDatabase
import com.project.organicoutlet.databinding.ActivityProductsListBinding
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsListBinding
    private lateinit var productDao: ProductDao
    private val scope = CoroutineScope(Dispatchers.IO)

    private val adapter = ProductsListAdapter { product ->
        openDetailsActivity(product.productId)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list)
        bindRecyclerViewAdapter()
        fabListener()
    }

    override fun onResume() {
        super.onResume()
        val database = ProductDatabase.getInstance(this)
        productDao = database.productDao()

        scope.launch {
            val products = productDao.getAllProducts()

            withContext(Dispatchers.Main) {
                adapter.update(products)
            }
        }
    }

    private fun openDetailsActivity(productId: Long) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("productId", productId)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_asc -> {
                fetchAndDisplayProducts { productDao.getProductsOrdAsc() }
                return true
            }
            R.id.filter_desc -> {
                fetchAndDisplayProducts { productDao.getProductsOrdDesc() }
                return true
            }
            R.id.filter_creat -> {
                fetchAndDisplayProducts { productDao.getAllProducts() }
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun fetchAndDisplayProducts(fetchProducts: suspend () -> List<Product>) {
        scope.launch {
            val products = withContext(Dispatchers.IO) {
                fetchProducts()
            }
            withContext(Dispatchers.Main) {
                adapter.update(products)
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