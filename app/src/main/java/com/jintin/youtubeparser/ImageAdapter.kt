package com.jintin.youtubeparser

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

@SuppressLint("SetJavaScriptEnabled")
class ImageViewHolder(private val binding: AdapterImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webChromeClient = WebChromeClient()
    }

    fun bind(url: String) {
        val path =
            "<iframe src='https://www.youtube.com/embed/$url' width='100%' height='100%' style='border: none;'></iframe>"
        binding.webView.loadData(path, "text/html", "utf-8")
    }
}