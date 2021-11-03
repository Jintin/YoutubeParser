package com.jintin.youtubeparser

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jintin.youtubeparser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ImageAdapter()

        viewModel.liveData.observe(this, adapter::submitList)
        viewModel.stateLiveData.observe(this) {
            binding.progressBar.visibility = if (it == State.LOADING) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        binding.searchButton.setOnClickListener {
            viewModel.search(binding.editText.text.toString())
        }
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}