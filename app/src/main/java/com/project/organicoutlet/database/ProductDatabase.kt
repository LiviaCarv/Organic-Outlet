package com.project.organicoutlet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.organicoutlet.database.converter.Converters

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}