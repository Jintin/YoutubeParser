package com.jintin.youtubeparser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jintin.youtubeparser.databinding.AdapterImageBinding

class StringDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class ImageAdapter : ListAdapter<String, ImageViewHolder>(StringDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(AdapterImageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ImageViewHolder(private val binding: AdapterImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(url: String) {
        Glide.with(binding.root).load(url).into(binding.imageView)
    }
}