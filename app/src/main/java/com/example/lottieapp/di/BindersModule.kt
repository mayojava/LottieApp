package com.example.lottieapp.di

import com.example.lottieapp.data.network.connectivity.ConnectivityManagerImpl
import com.example.lottieapp.data.network.connectivity.InternetConnectivityManager
import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import com.example.lottieapp.data.network.dispatchers.AppDispatchersImpl
import com.example.lottieapp.data.network.repository.RemoteRepository
import com.example.lottieapp.data.network.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindersModule {
    @Binds
    @Singleton
    fun bindAppDispatchers(dispatchersImpl: AppDispatchersImpl): AppDispatchers

    @Binds
    @Singleton
    fun bindInterConnectivityManager(connectivityManagerImpl: ConnectivityManagerImpl): InternetConnectivityManager

    @Binds
    @Singleton
    fun bindRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository
}
