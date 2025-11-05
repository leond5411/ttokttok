package com.kau.ttokttok.data.remote.dto.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Backend ApiResponse<T> 대응:
 * fields: isSuccess, code, message, result
 */
@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "isSuccess") val isSuccess: Boolean?,
    @Json(name = "code") val code: String?,
    @Json(name = "message") val message: String?,
    @Json(name = "result") val result: T?
)
