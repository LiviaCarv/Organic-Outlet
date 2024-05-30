package com.project.organicoutlet.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.project.organicoutlet.database.AppDatabase
import com.project.organicoutlet.model.User
import com.project.organicoutlet.preferences.dataStore
import com.project.organicoutlet.preferences.userPreferences
import com.project.organicoutlet.ui.extensions.changeActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class UserBaseActivity : AppCompatActivity() {
    private var _currentUser: MutableStateFlow<User?> = MutableStateFlow(null)
    protected val currentUser: StateFlow<User?> = _currentUser

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            verifyUserLogged()
        }
    }

    private suspend fun verifyUserLogged() {
        dataStore.data.collect { preferences ->

            preferences[userPreferences]?.let {

                searchUser(it)
            } ?: openLoginActivity()
        }
    }

    private suspend fun searchUser(userId: String) {
        _currentUser.value = userDao.searchUserById(userId).firstOrNull()
    }

    protected suspend fun userLogOut() {
        dataStore.edit { preferences ->
            preferences.remove(userPreferences)
        }
    }

    private fun openLoginActivity() {
        changeActivity(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }

}