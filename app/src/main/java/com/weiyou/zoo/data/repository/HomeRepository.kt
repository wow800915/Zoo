package com.weiyou.zoo.data.repository

import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.data.network.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


internal class HomeRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getAreaList(): Flow<NetworkResult<AreaList>> {
        return flow{
            emit(NetworkResult.Loading)
            // Call the API using the remoteDataSource
            val result = remoteDataSource.getAreaList()
            // Emit the result
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}