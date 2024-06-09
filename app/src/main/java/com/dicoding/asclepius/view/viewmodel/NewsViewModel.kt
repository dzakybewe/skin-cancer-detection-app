package com.dicoding.asclepius.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.api.ApiConfig
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.data.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    companion object {
        private const val TAG = "NewsViewModel"
    }

    private val _listNews = MutableLiveData<List<ArticlesItem>>()
    val listNews: LiveData<List<ArticlesItem>> = _listNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    init {
        getCancerNews()
    }

    private fun getCancerNews() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getCancerNews()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>,
            ) {

                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _listNews.value = response.body()?.articles
                        _message.value = "success"
                    } else {
                        Log.e(TAG, "onResponseMessage: ${response.message()}")
                        _message.value = response.message()
                    }
                }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _message.value = t.message
            }
        })
    }
}