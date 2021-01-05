package com.example.android.observability.ui.blog

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.observability.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_next.*

@AndroidEntryPoint
class BlogActivity : AppCompatActivity(R.layout.activity_next) {

    private val viewModel: BlogViewModel by viewModels()
    private val owner = this@BlogActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        onObserve()
    }

    private fun onObserve() {
    }

    companion object {
        internal val TAG = BlogActivity::class.java.simpleName
    }
}