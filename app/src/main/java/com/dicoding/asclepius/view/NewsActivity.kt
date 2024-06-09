package com.dicoding.asclepius.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.dicoding.asclepius.helper.showToast
import com.dicoding.asclepius.view.adapters.NewsAdapter
import com.dicoding.asclepius.view.viewmodel.NewsViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Cancer News"
        }

        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        newsViewModel.listNews.observe(this) {
            setNewsList(it)
        }

        newsViewModel.isLoading.observe(this) {
            if (it) {
                binding.progressBarNews.visibility = View.VISIBLE
                binding.tvNewsNotFound.visibility = View.GONE
            } else {
                binding.progressBarNews.visibility = View.GONE
            }
        }

        newsViewModel.message.observe(this) {
            if (it != "success") {
                binding.tvNewsNotFound.visibility = View.VISIBLE
            }
        }
    }

    private fun setNewsList(listNews: List<ArticlesItem>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager

        val adapter = NewsAdapter(listNews)
        binding.rvNews.adapter = adapter
        adapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ArticlesItem) {
                showToast(this@NewsActivity, "Clicked on ${data.title}")
            }
        })
    }
}