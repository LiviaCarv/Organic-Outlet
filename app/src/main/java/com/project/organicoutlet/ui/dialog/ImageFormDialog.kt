package com.project.organicoutlet.ui.dialog

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import coil.load
import com.project.organicoutlet.databinding.ActivityProductFormBinding
import com.project.organicoutlet.databinding.AddImageFormBinding
import com.project.organicoutlet.extensions.loadImage

class ImageFormDialog(val context: Context) {

    fun showAlertDialog(binding: ActivityProductFormBinding) {
        val bindingImageForm = AddImageFormBinding.inflate(LayoutInflater.from(context))

        bindingImageForm.fabLoadImage.setOnClickListener {
            val url = bindingImageForm.edtUrl.text.toString()
            bindingImageForm.imgProductProfile.load(url)
        }

        AlertDialog.Builder(context)
            .setView(bindingImageForm.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val imageUrl = bindingImageForm.edtUrl.text.toString()
                binding.edtImgProduct.loadImage(imageUrl)
            }
            .setNegativeButton("Cancelar") { _, _ -> }
            .show()

    }
}