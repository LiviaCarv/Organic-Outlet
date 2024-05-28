package com.project.organicoutlet.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.project.organicoutlet.database.AppDatabase
import com.project.organicoutlet.databinding.ActivityLoginBinding
import com.project.organicoutlet.preferences.dataStore
import com.project.organicoutlet.preferences.userPreferences
import com.project.organicoutlet.ui.extensions.changeActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }

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
            lifecycleScope.launch {
                userDao.authenticate(user, password)?.let { user ->
                    dataStore.edit { preferences ->
                        preferences[userPreferences] = user.id
                    }
                    changeActivity(ProductsListActivity::class.java)
                } ?: Toast.makeText(
                    this@LoginActivity,
                    "Authentication failed!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.fabRegister.setOnClickListener {
            changeActivity(RegisterUserActivity::class.java)
        }
    }
}