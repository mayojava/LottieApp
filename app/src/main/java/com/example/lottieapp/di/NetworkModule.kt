package com.example.lottieapp.di

import com.example.lottieapp.BuildConfig
import com.example.lottieapp.data.LottieAnimApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [TokensModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideLottieApi(retrofit: Retrofit): LottieAnimApi{
        return retrofit.create(LottieAnimApi::class.java)
    }

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    internal fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url

                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("alt", "media")
                    .build()

                val newRequest = originalRequest.newBuilder().url(newUrl).build()

                chain.proceed(newRequest)
            }
            .build()
    }
}