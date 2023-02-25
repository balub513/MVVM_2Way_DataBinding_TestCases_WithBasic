package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repo.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel constructor(var service: ApiService) : ViewModel() {

    private var _liveDataList: MutableLiveData<List<String>> = MutableLiveData()
    var liveDataList: LiveData<List<String>> = _liveDataList


    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiUsers = service.getApiUsers()
            _liveDataList.postValue(apiUsers)
        }
    }


    private fun getLiveDataListFromRepo(): List<String> {
        return listOf("Balu", "Ram", "Raj", "Sourav")
    }
}