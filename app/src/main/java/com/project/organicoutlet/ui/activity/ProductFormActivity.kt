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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProductFormBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_product_form)
        val dao = ProductsDao()

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val description = binding.edtDescription.text.toString()
            val price = binding.edtPrice.text.toString()
            val product = Product(name, description)

            if (price.isNotEmpty()) {
                product.price = BigDecimal(price)
            }

            dao.insert(product)

            finish()
        }
    }


}
