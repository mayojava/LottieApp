package com.example.lottieapp.ui.home

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.lottieapp.R
import com.example.lottieapp.data.local.AnimationEntity

@Composable
fun Animations(title: String, animations: List<AnimationEntity>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(modifier = Modifier.padding(end = 16.dp)) {
            items(animations) { animation ->
                AnimationItem(
                    animationEntity = animation,
                    modifier = Modifier
                        .size(width = 240.dp, 280.dp)
                        .padding(start = 16.dp)
                )
            }
        }

    }
}

@Composable
fun AnimationItem(animationEntity: AnimationEntity, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()

    Card(
        elevation = 4.dp,

        modifier = modifier
    ) {
        Column {
            Box(modifier = Modifier.background(color = animationEntity.bgColor.toJetpackColor())) {
                animationEntity.gifUrl?.let {
                    Image(
                        painter = rememberImagePainter(
                            imageLoader = imageLoader,
                            data = it
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                } ?: Image(
                    painter = rememberImagePainter(
                        data = animationEntity.imageUrl,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            Surface(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    NetworkImage(
                        url = animationEntity.createdBy.avatarUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = animationEntity.name,
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = animationEntity.createdBy.authorName,
                            style = MaterialTheme.typography.body2.copy(fontSize = 10.sp)
                        )
                    }
                }
            }
        }
    }
}

internal fun String?.toJetpackColor(): Color {
    val color = this?.let {
        if (it.length < 7) {
            Color.White
        } else {
            Color(android.graphics.Color.parseColor(this))
        }
    } ?: Color.White
    return color
}