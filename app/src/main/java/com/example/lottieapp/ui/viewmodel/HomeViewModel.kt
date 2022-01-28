package com.example.lottieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lottieapp.data.repository.LottieAnimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val lottieAnimRepository: LottieAnimRepository
): ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    init {
        viewModelScope.launch {
            lottieAnimRepository
                .getBlogs()
                .catch { _viewState.value = ViewState.Error(it) }
                .collect { _viewState.value = ViewState.Loaded().copy(blogs = it) }
        }
    }
}