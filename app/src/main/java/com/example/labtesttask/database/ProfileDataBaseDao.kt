package com.example.labtesttask.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Update
    fun update(profile: Profile)

    @Query("SELECT * FROM profile_table")
    fun getProfile(): LiveData<Profile>

    @Query("DELETE FROM profile_table")
    fun clear()
}