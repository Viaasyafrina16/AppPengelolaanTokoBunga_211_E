package com.example.tokobungafloravie.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tokobungafloravie.room.entity.Admin

@Dao
interface AdminDao {

    @Insert
    suspend fun insert(admin: Admin)

    @Query("SELECT * FROM admin WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): Admin?

    @Query("SELECT * FROM admin WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): Admin?
}