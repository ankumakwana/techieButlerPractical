package com.example.techiebutlerpractical.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techiebutlerpractical.R
import com.example.techiebutlerpractical.models.Posts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, ListFragment()).addToBackStack("ListFrag").commit()
    }

    fun gotoDetailPage(item:Posts){
        val b = Bundle()
        b.putSerializable("ITEM",item)
        val fragment = DetailsFragment()
        fragment.arguments = b
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).addToBackStack("Details").commit()
    }
}