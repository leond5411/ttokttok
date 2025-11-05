package com.kau.ttokttok.data.remote.api

import com.kau.ttokttok.data.remote.dto.res.ApiResponse
import com.kau.ttokttok.data.remote.dto.res.TempExceptionDTO
import com.kau.ttokttok.data.remote.dto.res.TempTestDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Backend: @RequestMapping("/temp")
 */
interface TempApiService {

    /**
     * GET /temp/test
     * Response: ApiResponse<TempTestDTO>
     */
    @GET("/temp/test")
    suspend fun test(): Response<ApiResponse<TempTestDTO>>

    /**
     * GET /temp/exception?flag={int}
     * Response: ApiResponse<TempExceptionDTO>
     */
    @GET("/temp/exception")
    suspend fun exception(@Query("flag") flag: Int): Response<ApiResponse<TempExceptionDTO>>
}
