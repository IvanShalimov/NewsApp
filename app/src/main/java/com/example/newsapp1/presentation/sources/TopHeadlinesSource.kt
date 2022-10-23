package com.example.newsapp1.presentation.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp1.domain.SourcesRepository
import com.example.newsapp1.presentation.mapper.TopHeadlinesMapper
import com.example.newsapp1.presentation.models.ArticleItem
import javax.inject.Inject

class TopHeadlinesSource
@Inject constructor(
    private val repository: SourcesRepository,
    private val topHeadlinesMapper: TopHeadlinesMapper
): PagingSource<Int, ArticleItem>() {

    var source: String = ""

    override fun getRefreshKey(state: PagingState<Int, ArticleItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItem> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getNewTopHeaders(source = source, nextPage = nextPage)
            LoadResult.Page(
                data = topHeadlinesMapper.map(response),
                prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(
                e
            )
        }
    }

}