package com.example.shiftlabtesttask.profile

import java.io.Serializable

data class Profile(
    var name: String,
    var secondName: String,
    var password: String,
    var birthday: String
) : Serializable

