package com.project.abandonedpets

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.abandonedpets.databinding.ListItemBinding
import com.project.domain.model.AbandonedPets

class MainAdapter(private val context: Context) : RecyclerView.Adapter<MainAdapter.MainVH>() {
    var abandonedPetsList = listOf<AbandonedPets>()

    inner class MainVH(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AbandonedPets) {
            data.petGender = checkGender(data.petGender)
            data.neuterState = checkNeuterState(data.neuterState)

            binding.data = data
            Glide.with(context).load(data.petImg).into(binding.thumbnail)
            binding.thumbnail.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainVH(binding)
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.onBind(abandonedPetsList[position])
    }

    override fun getItemCount(): Int = abandonedPetsList.size

    fun checkGender(gender: String): String {
        return when (gender) {
            "M" -> "수컷"
            "F" -> "암컷"
            "Q" -> "미상"
            else -> gender
        }
    }

    fun checkNeuterState(neuterState: String): String {
        return when (neuterState) {
            "Y" -> "예"
            "N" -> "아니오"
            "U" -> "미상"
            else -> neuterState
        }
    }
}