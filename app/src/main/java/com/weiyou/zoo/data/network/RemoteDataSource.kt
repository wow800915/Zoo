package com.weiyou.zoo.data.network

import android.util.Log
import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.ErrorResponse
import com.weiyou.zoo.data.models.NetworkResult
import retrofit2.Response

internal class RemoteDataSource(private val networkManager: NetworkManager?) {

    suspend fun <T> getResponse(request: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val result = request.invoke()
            Log.d("RemoteDataSource", result.toString())
            if (result.isSuccessful) {
                // 200 ~ 300
                return NetworkResult.Success(result.body())
            } else {
                // 400 ~ 500
                NetworkResult.Error(ErrorResponse(result.code().toString(),result.message()))//這邊要改一下
            }
        } catch (e: Throwable) {
            Log.d("RemoteDataSource", "call api error exception ==== ${e.message}")
            NetworkResult.Error(ErrorResponse(message = e.message))
        }
    }

    suspend fun getAreaList(): NetworkResult<AreaList> {
        val areaListApi = networkManager?.create(ApiService::class.java)
        return getResponse(request = { areaListApi!!.getAreaList() })
    }

}