package com.project.organicoutlet.model

import java.io.Serializable
import java.math.BigDecimal


data class Product(
    val name: String,
    val description: String,
    var price: BigDecimal = BigDecimal.ZERO,
    var image: String? = null
) : Serializable
