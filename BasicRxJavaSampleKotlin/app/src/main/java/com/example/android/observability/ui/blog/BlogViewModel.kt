package com.example.android.observability.ui.blog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.observability.data.blog.Blog
import com.example.android.observability.repository.MainBlogRepository
import com.example.android.observability.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BlogViewModel
@ViewModelInject
constructor(
        private val mainBlogRepository: MainBlogRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    private val _blogTitle: MutableLiveData<CharSequence> = MutableLiveData()
    val blogTitle: LiveData<CharSequence>
        get() = _blogTitle

    fun onBlogItemClicekd(blogTitle: CharSequence) {
        _blogTitle.value = blogTitle
    }

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogEvents -> {
                    mainBlogRepository.getBlog()
                            .onEach { dataState -> _dataState.value = dataState }
                            .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {

                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()
    object None : MainStateEvent()
}