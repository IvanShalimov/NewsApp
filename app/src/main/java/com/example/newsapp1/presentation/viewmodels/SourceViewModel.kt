package com.example.newsapp1.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp1.domain.SourcesRepository
import com.example.newsapp1.domain.network.models.SourcesResponse
import com.example.newsapp1.presentation.mapper.SourcesItemMapper
import com.example.newsapp1.presentation.models.SourceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel
@Inject constructor(
    private val sourcesRepository: SourcesRepository,
    private val sourceMapper: SourcesItemMapper
) : ViewModel() {

    var sources by mutableStateOf(listOf<SourceItem>())

    fun getSources() {
        sourcesRepository.getSources(object : Callback<SourcesResponse> {
            override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                sources = sourceMapper.map(response.body() as SourcesResponse)
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                Log.d("test", "${t.message}")
            }
        })
    }

}