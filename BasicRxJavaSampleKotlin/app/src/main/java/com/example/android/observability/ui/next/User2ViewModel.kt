package com.example.android.observability.ui.next

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.observability.data.next.User2
import com.example.android.observability.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class User2ViewModel
@ViewModelInject
constructor(
        private val mainRepository: MainRepository
) : ViewModel() {

    private val _user2 = MutableLiveData<User2>()
    val user2: LiveData<User2> = _user2

    private val _userUpdate = MutableLiveData<Unit>()
    val userUpdate: LiveData<Unit> = _userUpdate

    private val disposable = CompositeDisposable()

    fun getUserName() {
        disposable.add(
                mainRepository.getUserById("1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _user2.value = it
                        }, { error ->
                            Log.e(User2Activity.TAG, "unable to get username", error)
                        })
        )
    }

    fun updateUserName(userName: String) {
        disposable.add(
                mainRepository.setUserName(User2("1", userName))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _userUpdate.value = Unit
                        }, { error ->
                            Log.e(User2Activity.TAG, "Unable to update username", error)
                        })
        )
    }


}