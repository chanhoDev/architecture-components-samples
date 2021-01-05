package com.example.android.observability.ui.blog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.observability.R
import com.example.android.observability.data.blog.Blog
import com.example.android.observability.databinding.ActivityBlogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_blog.*

@AndroidEntryPoint
class BlogActivity : AppCompatActivity() {

    private val viewModel: BlogViewModel by viewModels()
    private val owner = this@BlogActivity
    private lateinit var adapter: BlogAdapter
    private lateinit var binding:ActivityBlogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(owner,R.layout.activity_blog)
        setupRecyclerView()
        onObserve()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)

    }


    private fun onObserve() {
        with(viewModel) {
            dataState.observe(owner,{data ->
                displayLoading(false)
                populateRecyclerView(data)
            })
            blogTitle.observe(owner,{title->
                Toast.makeText(owner, title, Toast.LENGTH_SHORT).show()
            })
        }

    }

    private fun populateRecyclerView(blogs: List<Blog>) {
        if (!blogs.isNullOrEmpty()) {
            adapter.setItems(ArrayList(blogs))
        }
    }

    private fun setupRecyclerView() {
        adapter = BlogAdapter(viewModel)
        binding.blogRecyclerview.layoutManager = LinearLayoutManager(owner)
        binding.blogRecyclerview.adapter = adapter

    }

    private fun displayLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }


    companion object {
        internal val TAG = BlogActivity::class.java.simpleName
    }
}