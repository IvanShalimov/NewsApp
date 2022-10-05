package com.example.newsapp1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp1.R
import com.example.newsapp1.domain.network.NewsService
import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import com.example.newsapp1.presentation.mapper.SourcesItemMapper
import com.example.newsapp1.presentation.mapper.TopHeadlinesMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsService: NewsService by lazy { retrofit.create(NewsService::class.java) }

    private val sourceMapper: SourcesItemMapper by lazy { SourcesItemMapper() }
    private val topHeadlinesMapper by lazy { TopHeadlinesMapper() }
    private lateinit var recycler: RecyclerView
    private val adapter: SourcesAdapter by lazy { SourcesAdapter() }
    private val articleAdapter by lazy { ArticlesAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.items)
        recycler.layoutManager = LinearLayoutManager(this)

        val sourcesResponses = newsService.getSources("6ba37edae5004b5bb16ab6d1a3ae42cc")
        sourcesResponses.enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                val sources = sourceMapper.map(response.body() as SourcesResponse)
                recycler.adapter = adapter
                adapter.listener = {newCall(it.id)}
                adapter.items = sources
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                Log.d("test","${t.message}")
            }

        })
    }

    fun newCall(id: String) {
        newsService.getTopHeadlines(id, "6ba37edae5004b5bb16ab6d1a3ae42cc").enqueue(object : Callback<TopHeadlinesResponse> {
            override fun onResponse(call: Call<TopHeadlinesResponse>, response: Response<TopHeadlinesResponse>) {
                val headlines = topHeadlinesMapper.map(response.body() as TopHeadlinesResponse)
                articleAdapter.items = headlines
                recycler.adapter = articleAdapter
            }

            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                Log.d("test","${t.message}")
            }

        })
    }
}