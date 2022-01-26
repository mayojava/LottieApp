package com.example.lottieapp.ui.viewmodel

import com.example.lottieapp.data.local.BlogsEntity

sealed class ViewState {
    object Loading: ViewState()
    object Error: ViewState()
    data class Loaded(
        val blogs: List<BlogsEntity>
    ): ViewState()
}
