package com.example.lottieapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "data")
    val data: T
)

@JsonClass(generateAdapter = true)
data class Featured(
    @Json(name = "featured")
    val featured: Result<AnimationItem>
)

@JsonClass(generateAdapter = true)
data class Popular(
    @Json(name = "popular")
    val popular: Result<AnimationItem>
)

@JsonClass(generateAdapter = true)
data class Recent(
    @Json(name = "recent")
    val recent: Result<AnimationItem>
)

@JsonClass(generateAdapter = true)
data class Animators(
    @Json(name = "featuredAnimators")
    val featuredAnimators: AnimatorResult
)

@JsonClass(generateAdapter = true)
data class AnimatorResult(
    @Json(name = "results")
    val results: List<Animator>
)

@JsonClass(generateAdapter = true)
data class Blogs(
    @Json(name = "blogs")
    val blogs: Result<Blog>
)

@JsonClass(generateAdapter = true)
data class Result<T> (
    @Json(name = "currentPage")
    val currentPage: Int,
    @Json(name = "perPage")
    val perPage: Int,
    @Json(name = "totalPages")
    val totalPages: Int,
    @Json(name = "from")
    val from: Int,
    @Json(name = "to")
    val to: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "results")
    val results: List<T>
)

@JsonClass(generateAdapter = true)
data class AnimationItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "bgColor")
    val bgColor: String,
    @Json(name = "lottieUrl")
    val lottieUrl: String,
    @Json(name = "gifUrl")
    val gifUrl: String?,
    @Json(name = "videoUrl")
    val videoUrl: String?,
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "createdBy")
    val createdBy: CreatedBy
)

@JsonClass(generateAdapter = true)
data class CreatedBy (
    @Json(name = "name")
    val name: String,
    @Json(name = "avatarUrl")
    val avatarUrl: String
 )

@JsonClass(generateAdapter = true)
data class Animator(
    @Json(name = "name")
    val name: String,
    @Json(name = "avatarUrl")
    val avatarUrl: String
)

@JsonClass(generateAdapter = true)
data class Blog(
    @Json(name = "title")
    val title: String,
    @Json(name = "postedAt")
    val postedAt: String,
    @Json(name = "imageUrl")
    val imageUrl: String
)