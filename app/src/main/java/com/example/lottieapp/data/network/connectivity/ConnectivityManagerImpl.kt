package com.example.lottieapp.data.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class ConnectivityManagerImpl @Inject constructor(
    private val appDispatchers: AppDispatchers,
    @ApplicationContext private val context: Context
) : InternetConnectivityManager {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val cap: NetworkCapabilities = connectivityManager
                    .getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
                return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val networks: Array<Network> = connectivityManager.allNetworks
                for (n in networks) {
                    val nInfo: NetworkInfo? = connectivityManager.getNetworkInfo(n)
                    if (nInfo != null && nInfo.isConnected) return true
                }
            }
            else -> {
                val allNetworkInfo = connectivityManager.allNetworkInfo
                for (nInfo in allNetworkInfo) {
                    if (nInfo != null && nInfo.isConnected) return true
                }
            }
        }

        return false
    }

    override suspend fun hasInternetConnectivity(): Boolean {
        return withContext(appDispatchers.ioDispatcher) {
            if (isNetworkAvailable()) {
                try {
                    val connection = URL("https://clients3.google.com/generate_204").openConnection() as HttpsURLConnection
                    connection.setRequestProperty("User-Agent", "Android")
                    connection.setRequestProperty("Connection", "close")
                    connection.connectTimeout = 1000
                    connection.connect()

                    connection.responseCode == 204 && connection.contentLength == 0
                } catch (exception: Exception) {
                    false
                }
            } else {
                false
            }
        }
    }
}