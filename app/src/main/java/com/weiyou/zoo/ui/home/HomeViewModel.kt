package com.weiyou.zoo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


internal class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _areaListResult = MutableStateFlow<NetworkResult<AreaList>?>(null)
    val areaListResult: StateFlow<NetworkResult<AreaList>?> get() = _areaListResult.asStateFlow()

    fun getAreaList() {
        viewModelScope.launch {
            _areaListResult.value = NetworkResult.Loading // Optional: Set loading state before fetching data
            homeRepository.getAreaList().collectLatest { result ->
                _areaListResult.value = result
            }
        }
    }

}