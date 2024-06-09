package com.dicoding.asclepius.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.data.database.History
import com.dicoding.asclepius.databinding.HistoryRowBinding

class HistoryAdapter(private val dataList: List<History>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: HistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.tvHistoryResult.text = history.result
            binding.ivHistoryImage.setImageURI(history.imageUri.toUri())
            binding.tvHistoryDate.text = history.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = dataList[position]
        holder.bind(history)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(dataList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: History)
    }
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}