package com.project.organicoutlet.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.project.organicoutlet.databinding.AddImageFormBinding
import com.project.organicoutlet.extensions.loadImage

class ImageFormDialog(val context: Context) {

    fun showAlertDialog(previousUrl: String?, imageLoaded: (String) -> Unit) {
        AddImageFormBinding.inflate(LayoutInflater.from(context)).apply {
            fabLoadImage.setOnClickListener {
                val url = edtUrl.text.toString()
                imgProductProfile.loadImage(url)
            }
            previousUrl?.let {
                imgProductProfile.loadImage(it)
                edtUrl.setText(previousUrl)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirm") { _, _ ->
                    val imageUrl = edtUrl.text.toString()
                    imageLoaded(imageUrl)
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()

        }
    }


}