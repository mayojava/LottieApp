package com.example.lottieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lottieapp.data.network.dispatchers.AppDispatchers
import com.example.lottieapp.data.repository.LottieAnimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val lottieAnimRepository: LottieAnimRepository
): ViewModel() {
    private val _viewState = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState


}