package com.project.organicoutlet.dao


import com.project.organicoutlet.model.Product


class ProductsDao {
    companion object {
        private val products = mutableListOf<Product>()
    }


    fun insert(product: Product) {
        products.add(product)
    }

    fun getAllProducts() : List<Product> {
        return products.toList()
    }
}