package com.example.techiebutlerpractical.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Post")
data class Posts(@Ignore var userId:String="", @PrimaryKey var id:Long? = null, var title:String="", @Ignore var body:String="") :
    Serializable
