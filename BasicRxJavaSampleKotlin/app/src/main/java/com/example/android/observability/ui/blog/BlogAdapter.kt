package com.example.android.observability.ui.blog

import android.app.DownloadManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.observability.R
import com.example.android.observability.data.blog.Blog
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogAdapter(private val viewModel: BlogViewModel) : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    private val items = ArrayList<Blog>()

    fun setItems(items: ArrayList<Blog>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog,parent,false)
        return BlogViewHolder(view,viewModel)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bindView(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BlogViewHolder(itemView: View, private val viewModel: BlogViewModel) : RecyclerView.ViewHolder(itemView) {

        fun bindView(blogItem: Blog) {
            with(itemView){
                blog_layout.setOnClickListener {
                    viewModel.onBlogItemClicekd(blogItem.title)
                }
                text_title.text = blogItem.title
                text_description.text = blogItem.body
                Glide.with(itemView.blog_layout).load(blogItem.image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .apply(RequestOptions().centerCrop())
                        .into(itemView.image)
            }
        }
    }
}