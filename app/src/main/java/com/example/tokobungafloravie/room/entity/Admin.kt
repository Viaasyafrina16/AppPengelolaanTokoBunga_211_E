package com.example.tokobungafloravie.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val email: String,
    val password: String
)