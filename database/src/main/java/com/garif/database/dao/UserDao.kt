package com.garif.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.garif.database.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun get(): User?

    @Insert
    suspend fun save(user: User)
}