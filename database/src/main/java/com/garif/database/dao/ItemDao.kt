package com.garif.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.garif.database.model.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(item: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun get(id: String): Item?

    @Query("SELECT * FROM item")
    suspend fun get(): List<Item>

    @Query("DELETE FROM item")
    suspend fun delete()
}