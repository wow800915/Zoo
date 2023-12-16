package com.weiyou.zoo.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.ErrorResponse
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.data.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeRepository: HomeRepository

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        homeViewModel = HomeViewModel(homeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `getAreaList should update areaListResult with success result`() =
        testCoroutineScope.runBlockingTest {
            // Arrange
            val areaList = AreaList(/* Initialize with test data */)
            val successResult = NetworkResult.Success(areaList)
            `when`(homeRepository.getAreaList()).thenReturn(flowOf(successResult))

            // Act
            homeViewModel.getAreaList()

            // Assert
            verify(homeRepository).getAreaList()
            assert(homeViewModel.areaListResult.value == successResult)
        }

    @Test
    fun `getAreaList should update areaListResult with error result`() =
        testCoroutineScope.runBlockingTest {
            // Arrange
            val errorResult = NetworkResult.Error(ErrorResponse("404", "Not Found"))
            `when`(homeRepository.getAreaList()).thenReturn(flowOf(errorResult))

            // Act
            homeViewModel.getAreaList()

            // Assert
            verify(homeRepository).getAreaList()
            assert(homeViewModel.areaListResult.value == errorResult)
        }

    // Add more tests as needed for different scenarios (e.g., loading state)

}