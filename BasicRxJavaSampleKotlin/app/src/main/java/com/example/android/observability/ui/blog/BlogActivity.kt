package com.example.android.observability.ui.blog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.observability.R
import com.example.android.observability.data.blog.Blog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_blog.*

@AndroidEntryPoint
class BlogActivity : AppCompatActivity(R.layout.activity_blog) {

    private val viewModel: BlogViewModel by viewModels()
    private val owner = this@BlogActivity
    private lateinit var adapter: BlogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
//            dataState.observe(owner, { dataState ->
//                when (dataState) {
//                    is DataState.Success<List<Blog>> -> {
//                        displayLoading(false)
//                        populateRecyclerView(dataState.data)
//                    }
//                    is DataState.Loading -> {
//                        displayLoading(true)
//                    }
//                    is DataState.Error -> {
//                        displayLoading(false)
//                        displayError(dataState.exception.message)
//                    }
//                }
//            })
            blogTitle.observe(owner,{title->
                Toast.makeText(owner, title, Toast.LENGTH_SHORT).show()
            })
        }

    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun populateRecyclerView(blogs: List<Blog>) {
        if (!blogs.isNullOrEmpty()) {
            adapter.setItems(ArrayList(blogs))
        }
    }

    private fun setupRecyclerView() {
        adapter = BlogAdapter(viewModel)
        blog_recyclerview.layoutManager = LinearLayoutManager(this)
        blog_recyclerview.adapter = adapter
    }


    private fun displayLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }


    companion object {
        internal val TAG = BlogActivity::class.java.simpleName
    }
}