package com.weiyou.chanting.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiyou.chanting.data.models.AninalList
import com.weiyou.chanting.data.models.NetworkResult
import com.weiyou.chanting.data.repository.HomeRepository
import kotlinx.coroutines.launch


internal class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _aninalList  = MutableLiveData<NetworkResult<AninalList>>()
    val aninalList: LiveData<NetworkResult<AninalList>> get() = _aninalList

    fun getAninalList() {
        viewModelScope.launch {
                homeRepository.getAninalList().collect { result ->
                        _aninalList.postValue(result)
                }
        }
    }

}