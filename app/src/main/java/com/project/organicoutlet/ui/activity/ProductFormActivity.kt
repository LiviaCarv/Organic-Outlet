package com.project.organicoutlet.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import com.project.organicoutlet.R
import com.project.organicoutlet.dao.ProductsDao
import com.project.organicoutlet.databinding.ActivityProductFormBinding
import com.project.organicoutlet.databinding.AddImageFormBinding
import com.project.organicoutlet.extensions.loadImage
import com.project.organicoutlet.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    private val dao: ProductsDao = ProductsDao()
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_form)

        saveBtnListener()
        productImageListener()
    }

    private fun productImageListener() {
        binding.edtImgProduct.setOnClickListener {
            createDialog()
        }
    }

    private fun createDialog() {
        val bindingImageForm = AddImageFormBinding.inflate(layoutInflater)

        bindingImageForm.fabLoadImage.setOnClickListener {
           val url = bindingImageForm.edtUrl.text.toString()
            bindingImageForm.imgProductProfile.load(url)
        }

        AlertDialog.Builder(this)
            .setView(bindingImageForm.root)
            .setPositiveButton("Confirmar") { _, _ ->
                imageUrl = bindingImageForm.edtUrl.text.toString()
                binding.edtImgProduct.loadImage(imageUrl)

            }
            .setNegativeButton("Cancelar") { _, _ -> }
            .show()
    }

    private fun saveBtnListener() {
        binding.btnSave.setOnClickListener {
            val product = newProduct()
            dao.insert(product)
            finish()
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
