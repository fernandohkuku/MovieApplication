package com.fernando.ku.movieapp.di

import android.content.Context
import androidx.room.Room
import com.fernando.ku.movieapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies_db"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

}