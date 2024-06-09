package com.dicoding.asclepius.view.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.database.History
import com.dicoding.asclepius.data.database.HistoryRepository

class HistoryViewModel(application: Application): ViewModel() {
    private val mHistoryRepository: HistoryRepository = HistoryRepository(application)

    fun insertHistory(history: History) {
        mHistoryRepository.insert(history)
    }

    fun getHistory(): LiveData<List<History>> = mHistoryRepository.getHistory()
}