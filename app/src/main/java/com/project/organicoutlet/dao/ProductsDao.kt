package com.project.organicoutlet.dao


import com.project.organicoutlet.model.Product
import java.math.BigDecimal

class ProductsDao {
    companion object {
        private val products =
            mutableListOf<Product>(Product("Cesta", "frutas", BigDecimal("19.99")))
    }

    fun insert(product: Product) {
        products.add(product)
    }

    fun getAllProducts(): List<Product> {
        return products.toList()
    }
}