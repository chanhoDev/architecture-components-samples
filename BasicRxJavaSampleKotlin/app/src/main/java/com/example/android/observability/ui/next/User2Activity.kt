package com.example.android.observability.ui.next

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.observability.Injection
import com.example.android.observability.R
import com.example.android.observability.persistence.next.User2Dao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_next.*
import javax.inject.Inject

@AndroidEntryPoint
class User2Activity : AppCompatActivity(R.layout.activity_next) {
    private lateinit var viewModelFactory: ViewModel2Factory

    private val viewModel: User2ViewModel by viewModels {
        viewModelFactory
    }
    private val owner = this@User2Activity

    @Inject
    lateinit var user2Dao: User2Dao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = Injection.proivideViewModel2Factory(user2Dao)
        update_user_button.setOnClickListener {
            update_user_button.isEnabled = false
            viewModel.updateUserName(user_name_input.text.toString())
        }
        viewModel.getUserName()
    }

    override fun onStart() {
        super.onStart()
        onObserve()
    }

    private fun onObserve() {
        with(viewModel) {
            user2.observe(owner, { user2 ->
                user_name.text = user2.userName
            })
            userUpdate.observe(owner, {
                update_user_button.isEnabled = true
            })
        }
    }

    companion object {
        internal val TAG = User2Activity::class.java.simpleName
    }
}