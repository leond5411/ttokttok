package com.kau.ttokttok.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

/**
 * Backend: RootController
 * GET /health → plain text "healthy"
 */
interface RootApiService {
    @GET("/health")
    suspend fun health(): Response<String>
}
