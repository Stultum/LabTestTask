package com.example.labtesttask.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labtesttask.repository.ProfileRepository

class ViewModelFactory(private val profileRepository: ProfileRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(ProfileRepository::class.java).newInstance(profileRepository)
}