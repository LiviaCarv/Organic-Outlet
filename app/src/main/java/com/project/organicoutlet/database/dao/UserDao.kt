package com.project.organicoutlet.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.organicoutlet.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM USER WHERE id = :userId")
    fun searchUserById(userId: String) : Flow<User>
    @Query("SELECT * FROM USER WHERE id = :userId AND password = :password")
    suspend fun authenticate(userId: String, password: String) : User?
}