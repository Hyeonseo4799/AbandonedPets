package com.project.abandonedpets.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.project.abandonedpets.R
import com.project.abandonedpets.databinding.ActivityDetailsBinding
import com.project.domain.model.AbandonedPets
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var data: AbandonedPets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@DetailsActivity, R.layout.activity_details)

        data = intent.getSerializableExtra("data") as AbandonedPets

        data.petAge = calculateAge(data.petAge)
        binding.details = data
        binding.activity = this@DetailsActivity

        Glide.with(this).load(data.image).into(binding.image)
    }

    fun call() {
        val tel = changePhone(data.shelterTel)
        startActivity(Intent("android.intent.action.DIAL", Uri.parse(tel)))
    }

    private fun changePhone(phone: String): String {
        val number = "tel:$phone"
        return number.replace("-", "")
    }

    private fun calculateAge(birthText: String): String {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy")
        val year = sdf.format(date).toInt()
        val birth = birthText.replace("(년생)", "").toInt()
        return "${year - birth + 1}살"
    }
}