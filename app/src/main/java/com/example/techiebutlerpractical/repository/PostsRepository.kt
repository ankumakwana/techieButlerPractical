package com.example.techiebutlerpractical.repository

import com.example.techiebutlerpractical.models.Posts
import com.example.techiebutlerpractical.utils.Api
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class PostsRepository:KoinComponent {
    private val api:Api by inject()
    suspend fun getPosts():Response<ArrayList<Posts>>{
        return api.getPosts()
    }
}