package com.example.lottieapp.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    internal fun provideLottieDatabase(@ApplicationContext context: Context): LottieDatabase {
        return Room.databaseBuilder(
            context,
            LottieDatabase::class.java,
            "lottie.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesLottieAnimationDao(database: LottieDatabase) = database.lottieAnimationDao()
}
