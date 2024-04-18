package com.example.techiebutlerpractical.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techiebutlerpractical.database.PostDao
import com.example.techiebutlerpractical.models.Posts
import com.example.techiebutlerpractical.repository.PostsRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class PostsViewModel : ViewModel() , KoinComponent {
    private val repository:PostsRepository by inject()
    private val postDao:PostDao by inject()

    suspend fun addPostToDb() {
        viewModelScope.launch{
            val resp = repository.getPosts().body()
                if (resp != null) {
                    postDao.insertPost(resp)
                }
        }
    }

    suspend fun getPosts(limit:Int,offset:Int): MutableLiveData<List<Posts>>{
        val postsLiveData: MutableLiveData<List<Posts>> = MutableLiveData<List<Posts>>()
        postsLiveData.value = postDao.getPost(limit,offset)
        return  postsLiveData
    }
}