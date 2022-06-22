package com.drtveladze.finalapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drtveladze.finalapp.databinding.ShopItemBinding
import com.drtveladze.finalapp.model.ShopListItem

class ShoppingListAdapter(val items: MutableList<ShopListItem>, val onClick: ((ShopListItem) -> Unit)) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = items[position]
            binding.title.text = item.name
            Glide.with(binding.image.context)
                .load(item.image)
                .into(binding.image)
            binding.root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShopItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }
}