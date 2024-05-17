package com.fernando.ku.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernando.ku.movieapp.data.local.dao.MovieDao
import com.fernando.ku.movieapp.data.local.dao.RemoteKeysDao
import com.fernando.ku.movieapp.data.local.dao.UserDao
import com.fernando.ku.movieapp.data.local.models.MovieModel
import com.fernando.ku.movieapp.data.local.models.RemoteKeys
import com.fernando.ku.movieapp.data.local.models.UserModel
import com.fernando.ku.movieapp.domain.entities.UserEntity

@Database(entities = [MovieModel::class, RemoteKeys::class, UserModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun userDao():UserDao
}