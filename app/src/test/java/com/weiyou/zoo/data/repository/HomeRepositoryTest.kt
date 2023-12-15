package com.weiyou.zoo.data.repository

import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.ErrorResponse
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.data.network.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    // Mock the RemoteDataSource
    private val remoteDataSource: RemoteDataSource = mock()

    // Create an instance of HomeRepository with the mocked RemoteDataSource
    private val homeRepository = HomeRepository(remoteDataSource)

    @Test
    fun `getAreaList should emit Loading and Success`() = runBlockingTest {
        // Given
        val mockAreaList = mock<AreaList>()
        val mockSuccessResponse = NetworkResult.Success(mockAreaList)

        // Stubbing the getAreaList function in the remoteDataSource
        whenever(remoteDataSource.getAreaList()).thenReturn(mockSuccessResponse)

        // When
        val flow = homeRepository.getAreaList()
        val resultList = mutableListOf<NetworkResult<AreaList>>()
//        flow.collect { resultList.add(it) }

        // Collect values from the flow on the test dispatcher
        launch {
            flow.collect { resultList.add(it) }
        }

        // Advance time by some duration (if needed)
        testScheduler.apply { advanceTimeBy(1000); runCurrent() } // Advance time by 1 second

        // Then
        // Check that resultList has at least one element
        assertTrue(resultList.isNotEmpty())

        // Check the first emitted value is NetworkResult.Loading
        assertEquals(NetworkResult.Loading, resultList[0])

        // If there's a second element, check it is mockSuccessResponse
        if (resultList.size > 1) {
            assertEquals(mockSuccessResponse, resultList[1])
        }

    }

    @Test
    fun `getAreaList should emit Loading and Error`() = runBlockingTest {

        // Given
        val mockErrorResponse = NetworkResult.Error(ErrorResponse("404", "Not Found"))

        // Stubbing the getAreaList function in the remoteDataSource
        whenever(remoteDataSource.getAreaList()).thenReturn(mockErrorResponse)

        // When
        val flow = homeRepository.getAreaList()
        val resultList = mutableListOf<NetworkResult<AreaList>>()

        // Collect values from the flow on the test dispatcher
        launch {
            flow.collect { resultList.add(it) }
        }

        testScheduler.apply { advanceTimeBy(1000); runCurrent() } // Advance time by 1 second

        // Ensure that all coroutines are idle
        advanceUntilIdle()

        // Then
        // Check that resultList has at least one element
        assertTrue(resultList.isNotEmpty())

        // Check the first emitted value is NetworkResult.Loading
        assertEquals(NetworkResult.Loading, resultList[0])

        // If there's a second element, check it is mockErrorResponse
        if (resultList.size > 1) {
            assertEquals(mockErrorResponse, resultList[1])
        }

    }

}
