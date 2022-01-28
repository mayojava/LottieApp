package com.example.lottieapp.fake

import com.example.lottieapp.data.LottieAnimApi
import com.example.lottieapp.data.model.*
import retrofit2.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class FakeLottieAnimationApi(private val isSuccess: Boolean) : LottieAnimApi {
    var isFeaturedApiCalled = false
    var isPopularApiCalled = false
    var isRecentApiCalled = false
    var isAnimatorsApiCalled = false
    var isBlogsApiCalled = false

    override suspend fun featured(token: String): Response<ApiResponse<Featured>> {
        isFeaturedApiCalled = true
        return if (isSuccess) {
            Response.success(ApiResponse(Featured(
                featured = createResult(listOf(createAnimationItem()))
            )))
        } else {
            Response.error(404, "Error occurred".toResponseBody(null))
        }
    }

    override suspend fun popular(token: String): Response<ApiResponse<Popular>> {
        isPopularApiCalled = true
        return if (isSuccess) {
            Response.success(ApiResponse(Popular(
                popular = createResult(listOf(createAnimationItem()))
            )))
        } else {
            Response.error(404, "Error occurred".toResponseBody(null))
        }
    }

    override suspend fun recent(token: String): Response<ApiResponse<Recent>> {
        isRecentApiCalled = true
        return if (isSuccess) {
            Response.success(ApiResponse(Recent(
                recent = createResult(listOf(createAnimationItem()))
            )))
        } else {
            Response.error(404, "Error occurred".toResponseBody(null))
        }
    }

    override suspend fun animators(token: String): Response<ApiResponse<Animators>> {
        isAnimatorsApiCalled = true
        return if (isSuccess) {
            Response.success(ApiResponse(
                Animators(
                featuredAnimators = AnimatorResult(
                    listOf(Animator(name = "Anim Author", avatarUrl = "http://author.jpeg"))
                )
                )))
        } else {
            Response.error(404, "Error occurred".toResponseBody(null))
        }
    }

    override suspend fun blogs(token: String): Response<ApiResponse<Blogs>> {
        isBlogsApiCalled = true
        return if (isSuccess) {
            Response.success(ApiResponse(Blogs(createResult(listOf(
                Blog(
                    title = "Learn Animations",
                    postedAt = "2020-11-20",
                    imageUrl = "https://avatar.png")
            )))))
        } else {
            Response.error(404, "Error occurred".toResponseBody(null))
        }
    }

    private fun <T> createResult(data: List<T>) : Result<T> {
        return Result(
            currentPage = 1,
            perPage = 1,
            totalPages = 1,
            from = 0,
            to = 2,
            total = 3,
            data
        )
    }

    fun createAnimationItem(): AnimationItem {
        return AnimationItem(
            id = 1,
            bgColor = "#fffff",
            lottieUrl = "https://lottie.json",
            gifUrl = "https://lottie.gif",
            videoUrl = "https://lottie.mp4",
            imageUrl = "https://lottie.png",
            createdAt = "2020-11-10",
            createdBy = CreatedBy(name = "", avatarUrl = ""),
            name = "Rau Lom"
        )
    }
}