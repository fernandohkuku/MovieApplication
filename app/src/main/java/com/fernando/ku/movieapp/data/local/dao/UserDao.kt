package com.fernando.ku.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernando.ku.movieapp.data.local.models.MovieModel
import com.fernando.ku.movieapp.data.local.models.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM usermodel ORDER BY id ASC LIMIT 1")
    suspend fun getUser(): UserModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserModel)

    @Query("Delete from usermodel")
    suspend fun deleteUser()
}