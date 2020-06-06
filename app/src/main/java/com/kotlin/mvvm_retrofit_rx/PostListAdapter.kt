package com.kotlin.mvvm_retrofit_rx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostListAdapter(private var listOfPost:List<Post>): RecyclerView.Adapter<PostListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return PostListViewHolder(view)
    }

    override fun getItemCount(): Int {

        return listOfPost.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val post = listOfPost[position]
        holder.title.text=post.title
        holder.uId.text=post.userId.toString()
        holder.body.text=post.body
    }
}

class PostListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val uId: TextView = itemView.findViewById(R.id.user_id)
    val body: TextView = itemView.findViewById(R.id.body)

}
