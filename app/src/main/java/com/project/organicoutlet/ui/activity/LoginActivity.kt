package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.organicoutlet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fabListener()
    }

    private fun fabListener() {
        binding.fabLogin.setOnClickListener {
            val intent = Intent(this, ProductsListActivity::class.java)
            startActivity(intent)
        }

        binding.fabRegister.setOnClickListener {
            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }
    }
}