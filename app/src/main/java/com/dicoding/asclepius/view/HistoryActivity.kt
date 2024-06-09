package com.dicoding.asclepius.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.view.adapters.HistoryAdapter
import com.dicoding.asclepius.data.database.History
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.view.viewmodel.HistoryViewModel
import com.dicoding.asclepius.view.viewmodel.ViewModelFactory

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Detection History"
        }

        historyViewModel = obtainViewModel(this@HistoryActivity)

        historyViewModel.getHistory().observe(this) {
            if (it.isEmpty()) {
                binding.tvHistoryEmpty.visibility = View.VISIBLE
            } else {
                binding.tvHistoryEmpty.visibility = View.GONE
            }

            setHistoryList(it)
        }
    }

    private fun setHistoryList(listHistory: List<History>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager

        val adapter = HistoryAdapter(listHistory)
        binding.rvHistory.adapter = adapter

        adapter.setOnItemClickCallback(object : HistoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: History) {
                showResultPage(data, this@HistoryActivity)
            }
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[HistoryViewModel::class.java]
    }

    private fun showResultPage(history: History, srcActivity: Context) {
        val intent = Intent(srcActivity, ResultActivity::class.java)
        intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, history.imageUri)
        intent.putExtra(ResultActivity.EXTRA_RESULT, history.result)
        srcActivity.startActivity(intent)
    }
}