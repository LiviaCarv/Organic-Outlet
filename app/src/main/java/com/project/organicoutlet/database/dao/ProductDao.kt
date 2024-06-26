package com.project.organicoutlet.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.project.organicoutlet.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM products_table")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM products_table WHERE productId = :id")
    fun getProductById(id: Long): Flow<Product>

    @Query("SELECT * FROM products_table WHERE id_user = :userId ORDER BY name ASC")
    fun getProductsOrdAsc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM products_table WHERE id_user = :userId ORDER BY name DESC")
    fun getProductsOrdDesc(userId: String): Flow<List<Product>>

    @Query("SELECT * FROM products_table WHERE id_user = :userId")
    fun getProductsByUser(userId: String): Flow<List<Product>>

}
