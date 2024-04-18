package com.example.techiebutlerpractical.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techiebutlerpractical.R
import com.example.techiebutlerpractical.models.Posts
import com.example.techiebutlerpractical.utils.ItemClickListener

class PostAdapter(val itemClickListener: ItemClickListener) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var postList=ArrayList<Posts>()
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.raw_post,parent,false)
      return ViewHolder(view)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = postList[position]
        holder.textViewId.text = item.id.toString()
        holder.textViewTitle.text = item.title
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(item)
        }
    }

    fun getItems():ArrayList<Posts>{
        return postList
    }
    fun setPostList(list:ArrayList<Posts>,page:Int){
        val size = this.postList.size
        if(page==1) this.postList = list else this.postList.addAll(list)
        val sizeNew = this.postList.size
        notifyItemRangeChanged(size, sizeNew)
    }
}