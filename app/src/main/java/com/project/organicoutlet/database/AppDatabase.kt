package com.project.organicoutlet.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.organicoutlet.database.converter.Converters
import com.project.organicoutlet.database.dao.ProductDao
import com.project.organicoutlet.database.dao.UserDao
import com.project.organicoutlet.model.Product
import com.project.organicoutlet.model.User

@Database(
    entities = [Product::class, User::class], version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "organic-outlet-database"
            ).build().also {
                db = it
            }
        }

    }
}