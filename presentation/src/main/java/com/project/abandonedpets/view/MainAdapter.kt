package com.project.abandonedpets.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.abandonedpets.databinding.HeaderBinding
import com.project.abandonedpets.databinding.ListItemBinding
import com.project.domain.model.AbandonedPets

private const val HEADER = 0
private const val ITEM = 1

class MainAdapter(private val context: Context, private val clickListener: ItemClickListener) : PagingDataAdapter<AbandonedPets, RecyclerView.ViewHolder>(ABANDONEDPETS_DIFF) {
    inner class MainVH(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AbandonedPets) {
            data.petGender = checkGender(data.petGender)
            data.neuterState = checkNeuterState(data.neuterState)

            binding.data = data
            Glide.with(context).load(data.petImg).into(binding.thumbnail)
            binding.thumbnail.clipToOutline = true
        }
    }

    inner class HeaderVH(private val binding: HeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setListener() {
            binding.listener = clickListener
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER else ITEM
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderVH -> holder.setListener()
            is MainVH -> getItem(position)?.let { holder.onBind(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = when (viewType) {
            HEADER -> HeaderBinding.inflate(LayoutInflater.from(context), parent, false)
            else -> ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        }
        return if (viewType == HEADER) HeaderVH(binding as HeaderBinding) else MainVH(binding as ListItemBinding)
    }

    companion object {
        private val ABANDONEDPETS_DIFF = object : DiffUtil.ItemCallback<AbandonedPets>() {
            override fun areItemsTheSame(oldItem: AbandonedPets, newItem: AbandonedPets): Boolean = oldItem === newItem
            override fun areContentsTheSame(oldItem: AbandonedPets, newItem: AbandonedPets): Boolean = oldItem == newItem
        }
    }

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

class ItemClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(item: View) = clickListener(item.id)
}

