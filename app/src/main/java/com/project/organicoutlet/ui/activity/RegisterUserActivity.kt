package com.project.organicoutlet.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.organicoutlet.R
import com.project.organicoutlet.databinding.ActivityRegisterUserBinding
import com.project.organicoutlet.model.User

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
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
            finish()
        }
    }

    private fun createNewUser(): User {
        val user = binding.edttxtUser.text.toString()
        val password = binding.edttxtPassword.text.toString()
        val name = binding.edttxtName.text.toString()
        return User(user, name, password)

    }
}