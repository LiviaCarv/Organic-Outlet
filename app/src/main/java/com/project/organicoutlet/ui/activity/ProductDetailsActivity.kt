package com.project.organicoutlet.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.organicoutlet.databinding.ActivityProductDetailsBinding


class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

}