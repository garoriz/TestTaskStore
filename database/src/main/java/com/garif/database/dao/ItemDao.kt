package com.garif.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.garif.database.model.Item

@Dao
interface ItemDao {
    @Insert
    suspend fun save(item: Item)
}