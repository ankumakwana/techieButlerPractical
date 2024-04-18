package com.example.techiebutlerpractical.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.techiebutlerpractical.models.Posts

@Database(entities = [Posts::class], version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun postDao():PostDao
}