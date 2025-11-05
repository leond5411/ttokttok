package com.kau.ttokttok.data.remote.dto.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Backend: TempResponse.TempTestDTO { String testString }
 */
@JsonClass(generateAdapter = true)
data class TempTestDTO(
    @Json(name = "testString") val testString: String?
)

/**
 * Backend: TempResponse.TempExceptionDTO { Integer flag }
 */
@JsonClass(generateAdapter = true)
data class TempExceptionDTO(
    @Json(name = "flag") val flag: Int?
)
