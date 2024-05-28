package com.project.organicoutlet.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.project.organicoutlet.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)
}