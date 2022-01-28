package com.example.lottieapp.fake

import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
class TestDispatchers : AppDispatchers {
    override val mainDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val ioDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val defaultDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
}