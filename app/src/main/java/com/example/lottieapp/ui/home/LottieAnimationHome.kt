package com.example.lottieapp.ui.home

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lottieapp.ui.viewmodel.HomeViewModel
import com.example.lottieapp.ui.viewmodel.ViewState

@Composable
fun LottieAnimationHome(viewModel: HomeViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val state = viewModel.viewState.collectAsState()

        when (state.value) {
            is ViewState.Loaded -> AnimationsHome(data = state.value as ViewState.Loaded)
            is ViewState.Error -> {}
            is ViewState.Loading -> LoadingView()
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun AnimationsHome(data: ViewState.Loaded) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        if (data.blogs.isNotEmpty()) {
            Blogs(blogs = data.blogs)
        }
    }
}