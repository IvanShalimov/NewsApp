package com.example.newsapp1.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp1.domain.SourcesRepository
import com.example.newsapp1.presentation.mapper.SourcesItemMapper
import com.example.newsapp1.presentation.models.SourceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SourceViewModel
@Inject constructor(
    private val sourcesRepository: SourcesRepository,
    private val sourceMapper: SourcesItemMapper
) : ViewModel() {

    var sources by mutableStateOf(listOf<SourceItem>())

    fun getSources() {

        CoroutineScope(Dispatchers.Main).launch {
            sources = sourceMapper.map(sourcesRepository.getSources())
        }
    }
}