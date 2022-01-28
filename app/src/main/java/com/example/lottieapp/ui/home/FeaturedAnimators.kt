package com.example.lottieapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lottieapp.R
import com.example.lottieapp.data.local.AnimatorsEntity

@Composable
fun FeaturedAnimators(
    animators: List<AnimatorsEntity>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.featured_animators),
            style = MaterialTheme.typography.subtitle1
        )
        LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(animators) {
                LottieAnimator(animator = it)
            }
        }
    }
}

@Composable
fun LottieAnimator(animator: AnimatorsEntity) {
    Box(
        modifier = Modifier.padding(end=16.dp).clip(CircleShape)
    ) {
        NetworkImage(
            url = animator.avatarUrl,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(
                    CircleShape
                )
        )
    }
}