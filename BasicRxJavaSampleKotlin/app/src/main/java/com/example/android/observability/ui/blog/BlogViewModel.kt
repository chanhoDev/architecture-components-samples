package com.example.android.observability.ui.blog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.observability.data.blog.Blog
import com.example.android.observability.repository.MainBlogRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogViewModel
@ViewModelInject
constructor(
        private val mainBlogRepository: MainBlogRepository
) : ViewModel() {

//    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
//    val dataState: LiveData<DataState<List<Blog>>>
//        get() = _dataState

    private val _dataState: MutableLiveData<List<Blog>> = MutableLiveData()
    val dataState: LiveData<List<Blog>>
        get() = _dataState

    private val _blogTitle: MutableLiveData<CharSequence> = MutableLiveData()
    val blogTitle: LiveData<CharSequence>
        get() = _blogTitle

    fun onBlogItemClicekd(blogTitle: CharSequence) {
        _blogTitle.value = blogTitle
    }

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        when (mainStateEvent) {
            is MainStateEvent.GetBlogEvents -> {
                mainBlogRepository.getBlog()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    _dataState.value = it
                                }
                            }
                        },{error ->

                        })

            }
            is MainStateEvent.None -> {

            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()
    object None : MainStateEvent()
}