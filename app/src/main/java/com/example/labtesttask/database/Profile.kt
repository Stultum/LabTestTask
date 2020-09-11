package com.example.labtesttask.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class Profile(
    @PrimaryKey
    var firstName: String,
    var secondName: String,
    var password: String,
    var birthDate: String
)

