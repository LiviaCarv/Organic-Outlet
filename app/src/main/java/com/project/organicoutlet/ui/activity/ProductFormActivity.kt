package com.project.organicoutlet.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.project.organicoutlet.R
import com.project.organicoutlet.dao.ProductsDao
import com.project.organicoutlet.databinding.ActivityProductFormBinding
import com.project.organicoutlet.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    private val dao: ProductsDao = ProductsDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_form)

        saveBtnListener()
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
        val product = Product(name, description)

        if (price.isNotEmpty()) {
            product.price = BigDecimal(price)
        }
        return product
    }


}
