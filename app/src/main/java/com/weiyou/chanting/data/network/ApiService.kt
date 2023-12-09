package com.weiyou.chanting.data.network

import com.weiyou.chanting.data.models.AninalList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getAninalList(): Response<AninalList>

}