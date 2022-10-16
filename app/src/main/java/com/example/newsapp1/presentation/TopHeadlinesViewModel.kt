package com.example.newsapp1.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp1.domain.SourcesRepository
import com.example.newsapp1.domain.network.models.TopHeadlinesResponse
import com.example.newsapp1.presentation.mapper.TopHeadlinesMapper
import com.example.newsapp1.presentation.models.ArticleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel
@Inject constructor(
    private val sourcesRepository: SourcesRepository,
    private val topHeadlinesMapper: TopHeadlinesMapper
): ViewModel() {

    var topHeadlines by mutableStateOf(listOf<ArticleItem>())

    fun getTopHeadlines(id: String) {
        sourcesRepository.getTopHeaders(id, object : Callback<TopHeadlinesResponse> {
            override fun onResponse(call: Call<TopHeadlinesResponse>, response: Response<TopHeadlinesResponse>) {
               topHeadlines = topHeadlinesMapper.map(response.body() as TopHeadlinesResponse)
            }

            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {

            }

        })
    }
}