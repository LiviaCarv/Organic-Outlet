package com.project.organicoutlet.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun toBigDecimal(value: Double?): BigDecimal {
        return value?.let { BigDecimal(value.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun toDouble(value: BigDecimal?): Double? {
        return value?.let { value.toDouble() }
    }
}