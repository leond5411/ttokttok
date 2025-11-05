package com.kau.ttokttok.data.remote.util

import com.kau.ttokttok.data.remote.dto.res.ApiResponse
import retrofit2.HttpException
import retrofit2.Response

/**
 * 명세 3·4단계 반영: 공통 래퍼 언래핑 + 에러 코드/메시지 보존
 * - HTTP 2xx + isSuccess=true + result!=null → result 반환
 * - 그 외 → ApiHttpError / ApiLogicalError 로 throw
 */
class ApiHttpError(val status: Int, val bodyCode: String?, val bodyMessage: String?) : RuntimeException(
    "HTTP $status (${bodyCode ?: "-"}) ${bodyMessage ?: ""}".trim()
)

class ApiLogicalError(val bodyCode: String?, val bodyMessage: String?) : RuntimeException(
    "API ${bodyCode ?: "-"} ${bodyMessage ?: ""}".trim()
)

@Suppress("ThrowsCount")
inline fun <reified T> Response<ApiResponse<T>>.unwrap(): T {
    // HTTP 레벨
    if (!isSuccessful) {
        val code = code()
        val body = body()
        throw ApiHttpError(code, body?.code, body?.message)
    }
    val body = body() ?: throw IllegalStateException("Empty body")
    if (body.isSuccess == true && body.result != null) {
        return body.result
    }
    // 백엔드 실패 포맷(isSuccess=false or result==null)일 때
    throw ApiLogicalError(body.code, body.message)
}
