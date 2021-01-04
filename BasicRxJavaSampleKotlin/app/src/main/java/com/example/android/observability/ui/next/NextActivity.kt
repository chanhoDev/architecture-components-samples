package com.example.android.observability.ui.next

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.observability.Injection
import com.example.android.observability.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity:AppCompatActivity(R.layout.activity_next) {
    private lateinit var viewModelFactory: ViewModel2Factory

    private val viewModel: NextViewModel by viewModels{
        viewModelFactory
    }

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewModelFactory = Injection.provideViewModel2Factory(this)
        update_user_button.setOnClickListener { updateUserName() }
    }

    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.userName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.user_name.text = it
                },{error -> Log.e(TAG,"unable to get username",error)}
                ))
    }

    private fun updateUserName() {
        val userName = user_name_input.text.toString()
        update_user_button.isEnabled = false

        disposable.add(viewModel.updateUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    update_user_button.isEnabled=true
                },{
                    error -> Log.e(TAG,"Unable to update username",error)
                }))
    }

    companion object{
        private val TAG = NextActivity::class.java.simpleName
    }
}