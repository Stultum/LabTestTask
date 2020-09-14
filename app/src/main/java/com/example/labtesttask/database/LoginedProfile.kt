package com.example.labtesttask.database


object LoginedProfile {
    lateinit var profile: Profile
        private set

    fun init(profile: Profile) {
        this.profile = profile
    }
}