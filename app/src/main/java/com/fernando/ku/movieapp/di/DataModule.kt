package com.fernando.ku.movieapp.di

import com.fernando.ku.movieapp.data.local.source.AuthenticationLocalDataSourceImpl
import com.fernando.ku.movieapp.data.local.source.MovieLocalDataSourceImpl
import com.fernando.ku.movieapp.data.remote.source.AuthenticationRemoteDataSourceImpl
import com.fernando.ku.movieapp.data.repositories.MovieRemoteDataSource
import com.fernando.ku.movieapp.data.repositories.MovieRepositoryImpl
import com.fernando.ku.movieapp.data.remote.source.MovieRemoteDataSourceImpl
import com.fernando.ku.movieapp.data.repositories.AuthenticationLocalDataSource
import com.fernando.ku.movieapp.data.repositories.AuthenticationRemoteDataSource
import com.fernando.ku.movieapp.data.repositories.AuthenticationRepositoryImpl
import com.fernando.ku.movieapp.data.repositories.MovieLocalDataSource
import com.fernando.ku.movieapp.domain.repositories.AuthRepository
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindMovieRepository(
        repositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    internal abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource

    @Binds
    internal abstract fun bindAuthenticationLocalDataSource(
        localDataSourceImpl: AuthenticationLocalDataSourceImpl
    ): AuthenticationLocalDataSource

    @Binds
    internal abstract fun bindAuthenticationDataSource(
        remoteDataSourceImpl: AuthenticationRemoteDataSourceImpl
    ): AuthenticationRemoteDataSource

    @Binds
    internal abstract fun bindAuthenticationRepository(
        repositoryImpl: AuthenticationRepositoryImpl
    ): AuthRepository

    @Binds
    internal abstract fun bindMovieLocalDataSource(
        localDataSourceImpl: MovieLocalDataSourceImpl
    ): MovieLocalDataSource

}