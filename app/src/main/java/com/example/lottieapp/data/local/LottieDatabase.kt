package com.example.lottieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
    AnimationEntity::class,
    AnimatorsEntity::class,
    BlogsEntity::class], version = LottieDatabase.VERSION
)
abstract class LottieDatabase: RoomDatabase() {
    abstract fun lottieAnimationDao(): LottieAnimationDao

    companion object {
        const val VERSION = 1
    }
}
