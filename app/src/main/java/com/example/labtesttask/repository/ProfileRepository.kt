package com.example.labtesttask.repository

import androidx.lifecycle.LiveData
import com.example.labtesttask.database.Profile
import com.example.labtesttask.database.ProfileDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val dataBase: ProfileDataBase) {
    suspend fun writeProfile(profile: Profile) {
        withContext(Dispatchers.IO) {
            dataBase.profileDataBaseDao.insert(profile)
        }
    }

    suspend fun readProfile(profile: LiveData<Profile>) {
        withContext(Dispatchers.IO) {
            val profileDB = dataBase.profileDataBaseDao.getProfile()
            profile.value?.firstName = profileDB.value!!.firstName
            profile.value?.secondName = profileDB.value!!.secondName
            profile.value?.password = profileDB.value!!.password
            profile.value?.birthDate = profileDB.value!!.birthDate
        }
    }
}