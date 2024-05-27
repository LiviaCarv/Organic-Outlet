package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.organicoutlet.databinding.ActivityLoginBinding
import com.project.organicoutlet.ui.extensions.changeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fabListeners()
    }

    private fun fabListeners() {
        binding.fabLogin.setOnClickListener {
            val user = binding.edtxtUser.text.toString()
            val password = binding.edtxtPassword.text.toString()
            changeActivity(ProductsListActivity::class.java)
        }

        binding.fabRegister.setOnClickListener {
            changeActivity(RegisterUserActivity::class.java)
        }
    }
}