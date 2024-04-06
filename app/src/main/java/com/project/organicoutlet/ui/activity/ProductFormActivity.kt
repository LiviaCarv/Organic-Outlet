package com.project.organicoutlet.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.R
import com.project.organicoutlet.database.Product
import com.project.organicoutlet.database.ProductDao
import com.project.organicoutlet.database.ProductDatabase
import com.project.organicoutlet.databinding.ActivityProductFormBinding
import com.project.organicoutlet.extensions.loadImage
import com.project.organicoutlet.ui.dialog.ImageFormDialog
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    private lateinit var productDao: ProductDao
    private var imageUrl: String? = null
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_form)
        title = getString(R.string.register_product)

        if (intent.hasExtra("product")) {
            product = intent.getSerializableExtra("product") as? Product
            product?.let {
                binding.product = product
                binding.edtImgProduct.loadImage(product!!.image)
            }
            imageUrl = product!!.image
        }

        saveBtnListener()
        productImageListener()

        val database = ProductDatabase.getInstance(this)
        productDao = database.productDao()
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
