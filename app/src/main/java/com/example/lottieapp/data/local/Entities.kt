package com.example.lottieapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "animation"
)
data class AnimationEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "bg_color")
    val bgColor: String,
    @ColumnInfo(name = "lottie_url")
    val lottieUrl: String,
    @ColumnInfo(name = "gif_url")
    val gifUrl: String?,
    @ColumnInfo(name  = "video_url")
    val videoUrl: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded
    val createdBy: CreatedByEntity,
    @ColumnInfo(name = "type")
    val type: EntityType
)

@Entity(tableName = "animators")
data class AnimatorsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)

@Entity(tableName = "blogs")
data class BlogsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "posted_at")
    val postedAt: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

enum class EntityType { RECENT, POPULAR, FEATURED }

data class CreatedByEntity(
    val avatarUrl: String,
    val authorName: String
)