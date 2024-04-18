package com.example.techiebutlerpractical.database

import androidx.room.*
import com.example.techiebutlerpractical.models.Posts

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts: ArrayList<Posts>)

    @Query("SELECT * FROM post ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPost(limit: Int, offset: Int): List<Posts>

}