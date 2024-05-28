package com.project.organicoutlet.ui.extensions

import android.content.Context
import android.content.Intent


fun Context.changeActivity(
    clazz: Class<*>,
    intent: Intent.() -> Unit = {}
) {
    Intent(this, clazz)
        .apply {
            intent()
            startActivity(this)
        }
}