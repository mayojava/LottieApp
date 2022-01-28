package com.example.lottieapp.fake

import com.example.lottieapp.data.local.*

val recentAnimationEntity = AnimationEntity(
    id = 1,
    bgColor = "#ffffff",
    lottieUrl = "https://lottie.url.com",
    gifUrl = "https://lottie.anim.gif.com",
    videoUrl = "https://anim.mp4",
    imageUrl = "https://image.jpg",
    createdAt = "2021-12-04",
    createdBy = CreatedByEntity(
        avatarUrl = "https://avatar.jpg",
        authorName = "Bob Marley"
    ),
    name = "Hamburger animation",
    type = EntityType.RECENT
)

val popularAnimation = AnimationEntity(
    id = 1,
    bgColor = "#ffffff",
    lottieUrl = "https://lottie.url.com",
    gifUrl = "https://lottie.anim.gif.com",
    videoUrl = "https://anim.mp4",
    imageUrl = "https://image.jpg",
    createdAt = "2021-12-04",
    createdBy = CreatedByEntity(
        avatarUrl = "https://avatar.jpg",
        authorName = "Max Vestappen"
    ),
    name = "Swiping Animation",
    type = EntityType.POPULAR
)

val featuredAnimation = AnimationEntity(
    id = 1,
    bgColor = "#ffffff",
    lottieUrl = "https://lottie.url.com",
    gifUrl = "https://lottie.anim.gif.com",
    videoUrl = "https://anim.mp4",
    imageUrl = "https://image.jpg",
    createdAt = "2021-12-04",
    createdBy = CreatedByEntity(
        avatarUrl = "https://avatar.jpg",
        authorName = "Ray Johnson"
    ),
    name = "Solar system",
    type = EntityType.FEATURED
)

val blog = BlogsEntity(
    id = 0,
    title = "Making animations",
    postedAt = "2020-10-10",
    imageUrl = "https://article.image.png"
)

val animator = AnimatorsEntity(
    id = 0,
    name = "Ray Johnson",
    avatarUrl = "https://avatar.png"
)