package com.dicoding.asclepius.data.api

import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getCancerNews(
        @Query("q")
        q: String = "cancer",

        @Query("category")
        category: String = "health",

        @Query("language")
        language: String = "en",

        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ) : Call<NewsResponse>
}