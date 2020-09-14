package com.example.labtesttask.repository

import android.content.SharedPreferences
import com.example.labtesttask.database.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val PROFILE_PREF = "pp"
        private const val NAME = "name"
        private const val SECOND_NAME = "sName"
        private const val PASSWORD = "password"
        private const val BIRTHDAY = "birthday"
    }

    suspend fun isContainLoggedProfile() =
        withContext(Dispatchers.IO) { sharedPreferences.contains(NAME) }

    suspend fun writeProfile(profile: Profile) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit()
                .putString(NAME, profile.name)
                .putString(SECOND_NAME, profile.secondName)
                .putString(PASSWORD, profile.password)
                .putString(BIRTHDAY, profile.birthday)
                .apply()
        }
    }

    suspend fun readProfile() = withContext(Dispatchers.IO) {
        Profile(
            sharedPreferences.getString(NAME, "")!!,
            sharedPreferences.getString(SECOND_NAME, "")!!,
            sharedPreferences.getString(PASSWORD, "")!!,
            sharedPreferences.getString(BIRTHDAY, "")!!
        )
    }

    suspend fun clearProfile() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit()
                .remove(NAME)
                .remove(SECOND_NAME)
                .remove(PASSWORD)
                .remove(BIRTHDAY)
                .apply()
        }
    }
}