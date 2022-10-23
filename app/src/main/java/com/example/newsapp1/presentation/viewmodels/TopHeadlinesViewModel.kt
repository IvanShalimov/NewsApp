package com.example.newsapp1.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp1.presentation.sources.TopHeadlinesSource
import com.example.newsapp1.presentation.models.ArticleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel
@Inject constructor(
    private val source: TopHeadlinesSource
): ViewModel() {

    fun getNewTopHeadlines(id: String): Flow<PagingData<ArticleItem>> {
        source.source = id
        return Pager(config = PagingConfig(10), pagingSourceFactory = { source })
            .flow
            .cachedIn(viewModelScope)
    }
}