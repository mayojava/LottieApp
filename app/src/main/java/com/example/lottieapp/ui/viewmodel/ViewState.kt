package com.example.lottieapp.ui.viewmodel

import com.example.lottieapp.data.local.AnimationEntity
import com.example.lottieapp.data.local.AnimatorsEntity
import com.example.lottieapp.data.local.BlogsEntity

data class ViewData(
    val blogs: List<BlogsEntity> = listOf(),
    val animators: List<AnimatorsEntity> = listOf(),
    val featuredAnimations: List<AnimationEntity> = listOf(),
    val popularAnimations: List<AnimationEntity> = listOf(),
    val recentAnimations: List<AnimationEntity> = listOf()
)

data class ViewState (
    val isLoading: Boolean,
    val error: Throwable? = null,
    val data: ViewData = ViewData()
)
