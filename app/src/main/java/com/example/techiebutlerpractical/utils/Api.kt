package com.example.techiebutlerpractical.utils

import com.example.techiebutlerpractical.models.Posts
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET(URLFactory.BASE_URL)
    suspend fun getPosts() : Response<ArrayList<Posts>>
}