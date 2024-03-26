package com.project.organicoutlet.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.organicoutlet.R
import com.project.organicoutlet.databinding.ActivityMainBinding
import com.project.organicoutlet.model.Product
import com.project.organicoutlet.ui.recyclerview.adapter.ProductsListAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.productsList.adapter = ProductsListAdapter(
            listOf(
                Product("Cesta", "ksslkdjsl", BigDecimal("19.99")),
                Product("Segunda cesta", "ksslkdjsl", BigDecimal("27.99"))
            )
        )



    }
}