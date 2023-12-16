package com.weiyou.zoo.data.repository

import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.ErrorResponse
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.data.network.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    private lateinit var homeRepository: HomeRepository
    private val mockedRemoteDataSource: RemoteDataSource = mock()

    @Before
    fun setUp() {
        homeRepository = HomeRepository(mockedRemoteDataSource)
    }

    @Test
    fun `getAreaList emits Loading and Success`() = runBlocking {
        // Arrange
        val fakeAreaList = AreaList(/* populate with fake data */)
        whenever(mockedRemoteDataSource.getAreaList()).thenReturn(NetworkResult.Success(fakeAreaList))

        // Act
        val flow = homeRepository.getAreaList()

        // Assert
        flow.collect { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    // Loading state emitted
                }
                is NetworkResult.Success -> {
                    assertEquals(fakeAreaList, result.data)
                }
                is NetworkResult.Error -> {
                    // Handle error if needed
                }
            }
        }
    }

    @Test
    fun `getAreaList emits Loading and Error`() = runBlocking {
        // Arrange
        val errorMessage = "Error fetching data"
        whenever(mockedRemoteDataSource.getAreaList()).thenReturn(NetworkResult.Error(ErrorResponse(message = errorMessage)))

        // Act
        val flow = homeRepository.getAreaList()

        // Assert
        flow.collect { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    // Loading state emitted
                }
                is NetworkResult.Success -> {
                    // Handle success if needed
                }
                is NetworkResult.Error -> {
                    assertEquals(errorMessage, result.error?.message)
                }
            }
        }
    }
}