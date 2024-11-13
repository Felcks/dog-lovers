package com.matheus.doglovers.dogs.network.di

import android.content.Context
import com.matheus.doglovers.dogs.network.Constants.DOGS_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @InterceptorLogging
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        //TODO review this
        /*level = if (BuildConfig.BUILD_TYPE != "release") {

        } else {
            HttpLoggingInterceptor.Level.NONE
        }*/
    }

    @Provides
    @Singleton
    @DogsOkHttpClient
    fun provideOkHttpClient(
        @InterceptorLogging loggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            followRedirects(true)
            retryOnConnectionFailure(true)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    @DogsRetrofit
    fun provideRetrofit(
        @DogsOkHttpClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(DOGS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}