package com.example.labtesttask.profile

import com.example.shiftlabtesttask.profile.Profile

object LoginedProfile {
    lateinit var profile: Profile
        private set

    fun init(profile: Profile) {
        this.profile = profile
    }
}