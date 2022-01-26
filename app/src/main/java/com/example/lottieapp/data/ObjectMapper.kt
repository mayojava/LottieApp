package com.example.lottieapp.data

import com.example.lottieapp.data.local.AnimatorsEntity
import com.example.lottieapp.data.local.BlogsEntity
import com.example.lottieapp.data.model.Animator
import com.example.lottieapp.data.model.Blog

object ObjectMapper {
    fun fromBlogsEntity(list: List<BlogsEntity>) : List<Blog> {
        return list.map { Blog(
            title = it.title,
            postedAt = it.postedAt,
            imageUrl = it.imageUrl
        ) }
    }

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
}