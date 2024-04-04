package com.project.organicoutlet.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.organicoutlet.databinding.ActivityProductDetailsBinding
import com.project.organicoutlet.extensions.loadImage
import com.project.organicoutlet.model.Product


class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product") as? Product
        product?.let {
            binding.product = product
            binding.imgProductTop.loadImage(product.image)
        }


    }

}