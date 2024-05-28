package com.project.organicoutlet.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.project.organicoutlet.R
import com.project.organicoutlet.model.Product
import com.project.organicoutlet.database.dao.ProductDao
import com.project.organicoutlet.database.AppDatabase
import com.project.organicoutlet.databinding.ActivityProductFormBinding
import com.project.organicoutlet.extensions.loadImage
import com.project.organicoutlet.ui.dialog.ImageFormDialog
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    private var imageUrl: String? = null
    private var product: Product? = null

    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_form)
        tryLoadProduct()
        saveBtnListener()
        productImageListener()

    }

    private fun tryLoadProduct() {
        if (intent.hasExtra("productId")) {
            val productId = intent.getLongExtra("productId", -1L)
            lifecycleScope.launch {
                productDao.getProductById(productId).collect { product ->
                    product.let {
                        binding.product = product
                        binding.edtImgProduct.loadImage(product.image)
                        imageUrl = product.image

                    }
                }
                title = "Update product"

            }

        } else {
            title = getString(R.string.register_product)
        }
    }

    private fun productImageListener() {
        binding.edtImgProduct.setOnClickListener {
            ImageFormDialog(this).showAlertDialog(imageUrl) { url ->
                imageUrl = url
                binding.edtImgProduct.loadImage(imageUrl)
            }
        }
    }

    private fun saveBtnListener() {
        binding.btnSave.setOnClickListener {
            val newProduct = newProduct()
            if (newProduct.name.isEmpty()) {
                Toast.makeText(this, "Please insert the product name.", Toast.LENGTH_SHORT).show()
            } else if (newProduct.price == BigDecimal.ZERO) {
                Toast.makeText(this, "Please insert the product price.", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    if (product != null) {

                        product!!.apply {
                            name = newProduct.name
                            description = newProduct.description
                            price = newProduct.price
                            image = newProduct.image
                        }

                        productDao.update(product!!)
                    } else {
                        productDao.insert(newProduct)
                    }
                }
                finish()
            }

        }
    }

    private fun newProduct(): Product {
        val name = binding.edtName.text.toString()
        val description = binding.edtDescription.text.toString()
        val price = binding.edtPrice.text.toString()
        val product = Product(name = name, description = description, image = imageUrl)

        if (price.isNotEmpty()) {
            product.price = BigDecimal(price)
        }
        return product
    }


}
