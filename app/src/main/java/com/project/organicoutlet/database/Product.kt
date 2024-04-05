package com.project.organicoutlet.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal


@Entity(tableName = "products_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    var price: BigDecimal = BigDecimal.ZERO,

    @ColumnInfo(name = "image_url")
    var image: String? = null
) : Serializable
