package com.example.labtesttask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class ProfileDataBase: RoomDatabase() {
    abstract val profileDataBaseDao: ProfileDataBaseDao

    companion object{
        @Volatile
        private var INSTANCE: ProfileDataBase? = null
    }

    fun getInstance(context: Context) : ProfileDataBase {
        synchronized(this){
            var instance = INSTANCE

            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext, ProfileDataBase::class.java, "profile_database").fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}