package com.example.lottieapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LottieAnimationDao {
    @Query("SELECT * from animation where type = :type")
    fun getAllAnimations(type: EntityType): Flow<List<AnimationEntity>>
    @Query("SELECT * FROM animators")
    fun getAllAnimators(): Flow<List<AnimatorsEntity>>
    @Query("SELECT * FROM blogs")
    fun getAllBlogEntries(): Flow<List<BlogsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimations(entities: List<AnimationEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimators(animators: List<AnimatorsEntity>) {}
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogs: List<BlogsEntity>)

    @Transaction
    suspend fun deleteAndInsertAnimations(entities: List<AnimationEntity>) {
        deleteAllAnimations()
        insertAnimations(entities = entities)
    }

    @Transaction
    suspend fun deleteAndInsertBlogs(blogs: List<BlogsEntity>) {
        deleteAllBlogs()
        insertBlogs(blogs = blogs)
    }

    @Transaction
    suspend fun deleteAndInsertAnimators(animators: List<AnimatorsEntity>) {
        deleteAllAnimators()
        insertAnimators(animators = animators)
    }

    @Query("DELETE FROM animation")
    suspend fun deleteAllAnimations()
    @Query("DELETE FROM blogs")
    suspend fun deleteAllBlogs()
    @Query("DELETE FROM animators")
    suspend fun deleteAllAnimators()
}