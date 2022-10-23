package com.example.newsapp1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.newsapp1.presentation.ui.theme.NewsApp1Theme
import com.example.newsapp1.presentation.view.NewsSourcesList
import com.example.newsapp1.presentation.view.TopHeaders
import com.example.newsapp1.presentation.viewmodels.SourceViewModel
import com.example.newsapp1.presentation.viewmodels.TopHeadlinesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    private val sourceViewModel: SourceViewModel by viewModels()
    private val topHeadlineViewModel: TopHeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sourceViewModel.getSources()

        setContent {
            var isDetail by rememberSaveable { mutableStateOf(false) }
            var sourceId by rememberSaveable { mutableStateOf("")}

            NewsApp1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    if (isDetail) {
                        TopHeaders(topHeadlineViewModel.getNewTopHeadlines(sourceId))
                    } else {
                        NewsSourcesList(sourceViewModel.sources) { source ->
                            sourceId = source.id
                            isDetail = true
                        }
                    }
                }
                BackHandler {
                    isDetail = false
                }
            }
        }
    }
}
