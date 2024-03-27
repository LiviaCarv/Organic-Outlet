package com.project.organicoutlet.model

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String,
    var price: BigDecimal = BigDecimal.ZERO
)
