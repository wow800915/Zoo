package com.weiyou.chanting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _aninalList = MutableStateFlow<NetworkResult<AninalList>?>(null)
    val aninalList: StateFlow<NetworkResult<AninalList>?> get() = _aninalList.asStateFlow()

    fun getAninalList() {
        viewModelScope.launch {
            _aninalList.value = NetworkResult.Loading // Optional: Set loading state before fetching data
            homeRepository.getAninalList().collectLatest { result ->
                _aninalList.value = result
            }
        }
    }

}