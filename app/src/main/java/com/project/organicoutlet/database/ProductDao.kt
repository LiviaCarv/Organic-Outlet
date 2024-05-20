package com.project.organicoutlet.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM products_table")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM products_table WHERE productId = :id")
    fun getProductById(id: Long): Product

    @Query("SELECT * FROM products_table ORDER BY name ASC")
    fun getProductsOrdAsc(): List<Product>

    @Query("SELECT * FROM products_table ORDER BY name DESC")
    fun getProductsOrdDesc(): List<Product>

}
