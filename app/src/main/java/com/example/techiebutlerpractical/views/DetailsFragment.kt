package com.example.techiebutlerpractical.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.techiebutlerpractical.R
import com.example.techiebutlerpractical.models.Posts

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments?.getSerializable("ITEM") as Posts
        val textviewId = view.findViewById<TextView>(R.id.textViewId)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        textviewId.text = item.id.toString()
        textViewTitle.text = item.title
    }


}