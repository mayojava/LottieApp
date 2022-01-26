package com.example.lottieapp.data.network.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val mainDispatcher: CoroutineDispatcher
    val ioDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
}
