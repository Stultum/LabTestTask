package com.example.labtesttask.database

import java.io.Serializable

data class Profile(
    var name: String,
    var secondName: String,
    var password: String,
    var birthday: String
) : Serializable

