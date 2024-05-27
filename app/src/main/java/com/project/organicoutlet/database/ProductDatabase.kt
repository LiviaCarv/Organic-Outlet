package com.project.organicoutlet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.organicoutlet.database.converter.Converters
import com.project.organicoutlet.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private lateinit var db: ProductDatabase

        fun getInstance(context: Context): ProductDatabase {
            if (::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                ProductDatabase::class.java,
                "organic-outlet-database"
            ).build().also {
                    db = it
                }
        }

    }
}