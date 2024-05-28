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
            lifecycleScope.launch {
                try {
                    userDao.insert(newUser)
                    finish()
                } catch (exception: Exception) {
                    Toast.makeText(this@RegisterUserActivity, exception.message, Toast.LENGTH_SHORT).show()
                }
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