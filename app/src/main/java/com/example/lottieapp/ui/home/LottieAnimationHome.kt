package com.example.lottieapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lottieapp.R
import com.example.lottieapp.ui.viewmodel.HomeViewModel
import com.example.lottieapp.ui.viewmodel.ViewState
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun LottieAnimationHome(viewModel: HomeViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        val state = viewModel.viewState.collectAsState()

        if (state.value.isLoading) {
            LoadingView()
        } else {
            AnimationsHome(state = state.value)
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
fun AnimationsHome(state: ViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text = stringResource(id = R.string.app_name))
            }
        )

        if (state.data.featuredAnimations.isNotEmpty()) {
            Animations(
                stringResource(id = R.string.featured_animations),
                animations = state.data.featuredAnimations
            )
        }

        if (state.data.popularAnimations.isNotEmpty()) {
            Animations(
                stringResource(id = R.string.popular_animations),
                animations = state.data.popularAnimations
            )
        }

        if (state.data.recentAnimations.isNotEmpty()) {
            Animations(
                stringResource(id = R.string.recent_animations),
                animations = state.data.recentAnimations
            )
        }

        if (state.data.animators.isNotEmpty()) {
            FeaturedAnimators(animators = state.data.animators)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (state.data.blogs.isNotEmpty()) {
            Blogs(blogs = state.data.blogs)
        }

    }
}