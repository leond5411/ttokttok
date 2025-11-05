package com.kau.ttokttok.data.remote.repository

import com.kau.ttokttok.data.remote.api.TempApiService
import com.kau.ttokttok.data.remote.dto.res.TempExceptionDTO
import com.kau.ttokttok.data.remote.dto.res.TempTestDTO
import com.kau.ttokttok.data.remote.util.unwrap
import javax.inject.Inject

class TempRepository @Inject constructor(
    private val api: TempApiService
) {
    suspend fun getTest(): TempTestDTO =
        api.test().unwrap()

    suspend fun getException(flag: Int): TempExceptionDTO =
        api.exception(flag).unwrap()
}
