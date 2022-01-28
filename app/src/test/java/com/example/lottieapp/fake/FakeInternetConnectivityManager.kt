package com.example.lottieapp.fake

import com.example.lottieapp.data.network.connectivity.InternetConnectivityManager

class FakeInternetConnectivityManager : InternetConnectivityManager {
    var isAvailable = true

    fun setNetworkAvailable(available: Boolean) {
        isAvailable = available
    }

    override fun isNetworkAvailable(): Boolean = isAvailable

    override suspend fun hasInternetConnectivity(): Boolean = isAvailable
}
