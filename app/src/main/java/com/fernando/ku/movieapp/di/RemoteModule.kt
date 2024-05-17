package com.fernando.ku.movieapp.di

import com.fernando.ku.movieapp.data.remote.api.MovieService
import com.fernando.ku.movieapp.ui_ktx.headersFrom
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Named("apiKey")
    fun provideApiKey() = "c0823934438075d63f1dbda4023e76fc"

    @Provides
    @Named("accessToken")
    fun provideTokenAccess(): String = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMDgyMzkzNDQzODA3NWQ2M2YxZGJkYTQwMjNlNzZmYyIsInN1YiI6IjY1MDBmNzJkNTU0NWNhMDExYmE2N2RkYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4QxbpZq9Tj3uzhA8uv2qLNcCA7NIcGBHDzoC4bWv9t8"


    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMoshiConvertFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)


    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        @Named("BaseUrl") baseUrl: String,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)

    @Provides
    @Named("InterceptorAuthenticator")
    fun provideInterceptorAuthenticator(@Named("apiKey") apiKey: String) = Interceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url
        val url = originalUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
        val requestBuilder = original.newBuilder().url(url)
        chain.proceed(requestBuilder.build())
    }

    @Provides
    @Named("InterceptorAuthenticatorToken")
    fun provideInterceptorAuthenticatorToken(@Named("accessToken") accessToken: String) =
        Interceptor { chain ->
            val mapHeader = mapOf(
                "Authorization" to "Bearer $accessToken"
            )
            val builder = chain.request().newBuilder()
            val headers = headersFrom(mapHeader)
            builder.headers(headers)
            chain.proceed(builder.build())
        }

    @Singleton
    @Provides
    fun provideOkHttpBuilder(
        @Named("InterceptorAuthenticator") authenticatorInterceptor: Interceptor,
        @Named("InterceptorAuthenticatorToken") authenticatorTokenInterceptor: Interceptor
    ) =
        OkHttpClient.Builder().apply {
            addInterceptor(authenticatorInterceptor)
            addInterceptor(authenticatorTokenInterceptor)
        }.build()

    @Singleton
    @Provides
    fun provideRetrofit(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): Retrofit =
        retrofitBuilder.client(okHttpClient).build()


    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

}