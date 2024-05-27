package com.project.organicoutlet.ui.extensions

import android.content.Context
import android.content.Intent


fun Context.changeActivity(clazz: Class<*>) {
    Intent(this, clazz)
        .apply { startActivity(this) }
}