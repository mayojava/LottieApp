package com.example.lottieapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lottieapp.data.local.BlogsEntity

@Composable
fun Blogs(blogs: List<BlogsEntity>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Latest Posts",
            style = MaterialTheme.typography.h6
        )

        LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(blogs) { blog ->
                BlogItem(blog = blog, modifier = Modifier.padding(end = 12.dp))
            }
        }

    }
}

@Composable
fun BlogItem(
    blog: BlogsEntity,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.size(220.dp, 200.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                url = blog.imageUrl,
                contentDescription = "",
                modifier = Modifier.size(width = 220.dp, height = 140.dp)
            )
            Text(
                text = blog.title,
                color = Color.White,
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
            )
        }
    }
}