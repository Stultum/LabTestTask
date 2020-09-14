package com.example.labtesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labtesttask.repository.ProfileRepository
import kotlinx.coroutines.launch

class MainWindowViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _isAccountLeave = MutableLiveData<Boolean>()
    val isAccountLeave: LiveData<Boolean>
        get() = _isAccountLeave

    fun leaveAccount() {
        viewModelScope.launch {
            profileRepository.clearProfile()
            _isAccountLeave.value = true
        }
    }
}
