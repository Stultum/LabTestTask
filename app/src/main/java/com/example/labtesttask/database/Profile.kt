package com.example.labtesttask.database

import androidx.room.Entity

@Entity(tableName = "profile_table")
data class Profile(
    var firstName: String,
    var secondName: String,
    var password: String,
    var birthDate: String
)

