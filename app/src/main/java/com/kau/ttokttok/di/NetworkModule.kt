package com.kau.ttokttok.di

import com.kau.ttokttok.data.remote.api.RootApiService
import com.kau.ttokttok.data.remote.api.TempApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL 은 환경별로 교체하세요.
    private const val BASE_URL = "https://api.example.com/"

    @Provides @Singleton
    fun moshi(): Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides @Singleton
    fun logging(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    @Provides @Singleton
    fun okHttp(logging: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)              // debug only 권장
            // .addInterceptor(requestIdInterceptor) // 11단계 정책 적용 시
            // .addInterceptor(authInterceptor)     // 인증 도입 시
            .build()

    @Provides @Singleton
    fun retrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    @Provides @Singleton
    fun rootApi(retrofit: Retrofit): RootApiService =
        retrofit.create(RootApiService::class.java)

    @Provides @Singleton
    fun tempApi(retrofit: Retrofit): TempApiService =
        retrofit.create(TempApiService::class.java)
}
