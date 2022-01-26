package com.example.lottieapp.di

import com.example.lottieapp.BuildConfig
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TokensModule {
    @Named("tokens")
    fun provideTokensMap(): Map<String, String> {
        return mapOf(
            KEY_FEATURED to BuildConfig.FEATURED_TOKEN,
            KEY_RECENT to BuildConfig.RECENT_TOKEN,
            KEY_POPULAR to BuildConfig.POPULAR_TOKEN,
            KEY_ANIMATORS to BuildConfig.ANIMATORS_TOKEN,
            KEY_BLOGS to BuildConfig.BLOGS_TOKEN
        )
    }

    const val KEY_FEATURED = "featured"
    const val KEY_RECENT = "recent"
    const val KEY_POPULAR = "popular"
    const val KEY_ANIMATORS = "animators"
    const val KEY_BLOGS = "blogs"
}
