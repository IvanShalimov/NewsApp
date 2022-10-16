package com.example.newsapp1.di

import com.example.newsapp1.presentation.NewsActivity
import dagger.Component
import javax.inject.Inject

@Component
interface NewsComponent {

    @Inject
    fun inject(activity: NewsActivity)
}