package com.project.abandonedpets.view

import android.content.Context
import android.content.Intent
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

class MainAdapter(private val context: Context, private val headerClickListener: HeaderClickListener, private val itemClickListener: ItemClickListener) :
    PagingDataAdapter<AbandonedPets, RecyclerView.ViewHolder>(ABANDONEDPETS_DIFF) {
    inner class MainVH(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AbandonedPets, itemClickListener: ItemClickListener) {
            data.petGender = checkGender(data.petGender)
            data.neuterState = checkNeuterState(data.neuterState)

            binding.data = data
            Glide.with(context).load(data.thumbnail).into(binding.thumbnail)
            binding.thumbnail.clipToOutline = true
            binding.listener = itemClickListener
            binding.position = this.layoutPosition
        }
    }

    inner class HeaderVH(private val binding: HeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setListener() {
            binding.listener = headerClickListener
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderVH -> holder.setListener()
            is MainVH -> holder.onBind(getItem(position)!!, itemClickListener)

        }
        holder.itemView
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

    fun showDetails(position: Int) {
        val item = getItem(position)
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("data", item)
        context.startActivity(intent)
    }
}

class HeaderClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(item: View) = clickListener(item.id)
}

class ItemClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(position: Int) = clickListener(position)
}