package com.example.lottieapp.data.network.connectivity

interface InternetConnectivityManager {
    fun isNetworkAvailable(): Boolean
    suspend fun hasInternetConnectivity(): Boolean
}