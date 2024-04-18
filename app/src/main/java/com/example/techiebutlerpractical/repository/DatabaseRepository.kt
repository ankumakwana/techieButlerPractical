package com.example.techiebutlerpractical.repository

import com.example.techiebutlerpractical.database.AppDatabase
import com.example.techiebutlerpractical.models.Posts
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DatabaseRepository:KoinComponent {
    private val appDatabase: AppDatabase by inject()

    suspend fun getPostFromDb(limit:Int,offset:Int): List<Posts> {
        return appDatabase.postDao().getPost(limit,offset)
    }

    suspend fun addPostToDb(posts:ArrayList<Posts>){
        return appDatabase.postDao().insertPost(posts)
    }
}