package com.example.retrofitrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitrecyclerview.data.UserItem

class MyAdapter(private val context: Context, private val data: List<UserItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.profile_image)!!
        val name = view.findViewById<TextView>(R.id.tv_name)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(data[position].avatar_url).into(holder.image)
        holder.name.text = data[position].login
    }

    override fun getItemCount(): Int {
        return data.count()
    }
}