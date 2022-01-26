package com.example.lottieapp.data.network.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatchersImpl @Inject constructor() : AppDispatchers {
    override val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    override val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
}