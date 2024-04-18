package com.example.techiebutlerpractical.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techiebutlerpractical.R
import com.example.techiebutlerpractical.adapter.PostAdapter
import com.example.techiebutlerpractical.models.Posts
import com.example.techiebutlerpractical.utils.ItemClickListener
import com.example.techiebutlerpractical.viewmodels.PostsViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ListFragment : Fragment() {
    private val postsViewModel: PostsViewModel by inject()
    private lateinit var postAdapter: PostAdapter
    private var page=1
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewPost(view)
        getPostsFromApi()
    }

    private fun getPostsFromApi(){
        lifecycleScope.launch {
            postsViewModel.getPosts(10,(page-1)*10).observe(viewLifecycleOwner, Observer {
                postAdapter.setPostList(it as ArrayList<Posts>,page)
            })
        }
    }

    private fun setUpRecyclerViewPost(v:View){
        val recyclerView = v.findViewById<RecyclerView>(R.id.recycleViewPosts)
        layoutManager =  LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        postAdapter = PostAdapter(object : ItemClickListener{
            override fun onClick(item: Posts) {
                (activity as MainActivity).gotoDetailPage(item)
            }

        })

        recyclerView.adapter = postAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastCompletelyVisibleItemPosition() == postAdapter.getItems().size-1) {
                    page += 1
                    Log.d("sdsdsd","sdsd")
                    getPostsFromApi()
                }
            }
        })
    }
}