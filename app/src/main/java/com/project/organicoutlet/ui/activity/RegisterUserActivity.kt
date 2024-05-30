package com.project.organicoutlet.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.project.organicoutlet.R
import com.project.organicoutlet.database.AppDatabase
import com.project.organicoutlet.databinding.ActivityRegisterUserBinding
import com.project.organicoutlet.model.User
import com.project.organicoutlet.ui.extensions.toast
import kotlinx.coroutines.launch

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fabListeners()

    }

    private fun fabListeners() {
        binding.btnRegisterUser.setOnClickListener{
            val newUser = createNewUser()
            tryRegisterUser(newUser)
        }
    }

    private fun tryRegisterUser(newUser: User) {
        lifecycleScope.launch {
            try {
                userDao.insert(newUser)
                finish()
            } catch (exception: Exception) {
                toast("Please choose a different username; the current one is already in use.")
            }
        }
    }

    private fun createNewUser(): User {
        val user = binding.edttxtUser.text.toString()
        val password = binding.edttxtPassword.text.toString()
        val name = binding.edttxtName.text.toString()
        return User(user, name, password)
    }
}