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
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "price")
    var price: BigDecimal = BigDecimal.ZERO,

    @ColumnInfo(name = "image")
    var image: String? = null
) : Serializable
