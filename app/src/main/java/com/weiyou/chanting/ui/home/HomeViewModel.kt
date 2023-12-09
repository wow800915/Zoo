package com.weiyou.chanting.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiyou.chanting.data.models.AninalList
import com.weiyou.chanting.data.models.NetworkResult
import com.weiyou.chanting.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


internal class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _aninalListResult = MutableStateFlow<NetworkResult<AninalList>?>(null)
    val aninalListResult: StateFlow<NetworkResult<AninalList>?> get() = _aninalListResult.asStateFlow()

    fun getAninalList() {
        viewModelScope.launch {
            _aninalListResult.value = NetworkResult.Loading // Optional: Set loading state before fetching data
            homeRepository.getAninalList().collectLatest { result ->
                _aninalListResult.value = result
            }
        }
    }

}