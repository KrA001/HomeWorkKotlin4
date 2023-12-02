package com.example.homeworkkotlin4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnWhatsapp: MaterialButton
    private lateinit var etInput: EditText
    private lateinit var ivGallery: ImageView

    private val content = registerForActivityResult(ActivityResultContracts.GetContent()) {
        ivGallery.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnWhatsapp = this.findViewById(R.id.btn_whats_app)
        etInput = this.findViewById(R.id.et_test)
        ivGallery = this.findViewById(R.id.iv_gallery)

        goToWhatsapp()
        chooseImageFromGallery()
    }

    private fun goToWhatsapp() {
        btnWhatsapp.setOnClickListener {
            val whatsappSearchUrs = "https://wa.me/"
            val number = etInput.text.toString().trim()
            val whatsappIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$whatsappSearchUrs$number"))
            startActivity(whatsappIntent)
        }
    }

    private fun chooseImageFromGallery() {
        ivGallery.setOnClickListener {
            content.launch("image/*")
        }
    }
}
