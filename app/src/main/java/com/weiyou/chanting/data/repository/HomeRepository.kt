package com.weiyou.chanting.data.repository

import com.weiyou.chanting.data.models.AninalList
import com.weiyou.chanting.data.models.NetworkResult
import com.weiyou.chanting.data.network.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


internal class HomeRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getAninalList(): Flow<NetworkResult<AninalList>> {
        return flow{
            emit(NetworkResult.Loading)
            // Call the API using the remoteDataSource
            val result = remoteDataSource.getAninalList()
            // Emit the result
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}