package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.project.organicoutlet.R
import com.project.organicoutlet.database.Product
import com.project.organicoutlet.database.ProductDao
import com.project.organicoutlet.database.ProductDatabase
import com.project.organicoutlet.databinding.ActivityProductDetailsBinding
import com.project.organicoutlet.extensions.loadImage


class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var currentProduct: Product
    private lateinit var productDao: ProductDao

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val database = ProductDatabase.getInstance(this)
        productDao = database.productDao()
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        val productId = intent.getLongExtra("productId", -1L)
        val product = productDao.getProductById(productId)
        product?.let {
            currentProduct = product
            binding.product = product
            binding.imgProductTop.loadImage(product.image)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::currentProduct.isInitialized) {
            return when (item.itemId) {
                R.id.option_edit -> {
                    openFormActivityForEdition(currentProduct.productId)
                    finish()
                    true
                }

                R.id.option_delete -> {
                    productDao.delete(currentProduct)
                    finish()
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    private fun openFormActivityForEdition(productId: Long) {
        val intent = Intent(this, ProductFormActivity::class.java)
        intent.putExtra("productId", productId)
        startActivity(intent)
    }

}