package com.garif.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val keyId: Int,
    val id: String,
) {
    constructor(id: String) :
            this(0, id)
}
