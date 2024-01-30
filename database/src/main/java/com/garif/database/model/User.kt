package com.garif.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
) {
    constructor(name: String, surname: String, phoneNumber: String) :
            this(1, name, surname, phoneNumber)
}
