package com.dicoding.asclepius.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(application: Application) {
    private val mHistoryDao: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HistoryRoomDatabase.getDatabase(application)
        mHistoryDao = db.historyDao()
    }

    fun insert(history: History) {
        executorService.execute { mHistoryDao.insert(history) }
    }

    fun getHistory(): LiveData<List<History>> = mHistoryDao.getHistory()
}