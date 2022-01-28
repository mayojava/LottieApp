package com.example.lottieapp.ui.viewmodel

import com.example.lottieapp.data.local.BlogsEntity

sealed class ViewState {
    object Loading: ViewState()
    data class Error(val throwable: Throwable): ViewState()
    data class Loaded(
        val blogs: List<BlogsEntity> = listOf()
    ): ViewState()
}
