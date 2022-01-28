package com.example.lottieapp.data

import com.example.lottieapp.data.local.*
import com.example.lottieapp.data.model.AnimationItem
import com.example.lottieapp.data.model.Animator
import com.example.lottieapp.data.model.Blog

object ObjectMapper {
    fun toBlogDbEntity(list: List<Blog>) : List<BlogsEntity> {
        return list.map {
            BlogsEntity(title = it.title, postedAt = it.postedAt, imageUrl = it.imageUrl)
        }
    }

    fun toAnimatorDbEntity(result: List<Animator>): List<AnimatorsEntity> {
        return result.map {
            AnimatorsEntity(name = it.name, avatarUrl = it.avatarUrl)
        }
    }

    fun toAnimationDbEntity(type: EntityType, result: List<AnimationItem>): List<AnimationEntity> {
        return result.map {
            AnimationEntity(
                id = it.id,
                bgColor = it.bgColor,
                lottieUrl = it.lottieUrl,
                gifUrl = it.gifUrl,
                videoUrl = it.videoUrl,
                imageUrl = it.imageUrl,
                createdAt = it.createdAt,
                name = it.name,
                createdBy = CreatedByEntity(
                    avatarUrl = it.createdBy.avatarUrl,
                    authorName = it.createdBy.name
                ),
                type = type
            )
        }
    }
}