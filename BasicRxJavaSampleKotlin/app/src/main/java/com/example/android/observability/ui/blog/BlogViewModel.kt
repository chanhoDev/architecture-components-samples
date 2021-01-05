package com.example.android.observability.ui.blog

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.observability.data.next.User2
import com.example.android.observability.repository.MainBlogRepository
import com.example.android.observability.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BlogViewModel
@ViewModelInject
constructor(
        private val mainBlogRepository: MainBlogRepository
) : ViewModel() {

}