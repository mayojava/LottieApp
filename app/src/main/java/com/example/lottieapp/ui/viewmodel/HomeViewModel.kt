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
    factory: LottieAnimRepository.Factory
): ViewModel() {
    private val _viewState = MutableStateFlow(ViewState(isLoading = true))
    val viewState: StateFlow<ViewState> = _viewState

    init {
        val lottieAnimRepository = factory.create(viewModelScope)

        viewModelScope.launch {
            lottieAnimRepository
                .getBlogs()
                .catch { _viewState.value = _viewState.value.copy(error = it) }
                .collect {
                    _viewState.value =
                        _viewState.value.copy(
                            isLoading = it.isEmpty(),
                            data = _viewState.value.data.copy(blogs = it)
                        )
                }
        }

        viewModelScope.launch {
            lottieAnimRepository
                .getAnimators()
                .catch { _viewState.value = _viewState.value.copy(error = it) }
                .collect {
                    _viewState.value =
                        _viewState.value.copy(
                            isLoading = it.isEmpty(),
                            data = _viewState.value.data.copy(animators = it)
                        )
                }
        }

        viewModelScope.launch {
            lottieAnimRepository
                .getPopularAnimations()
                .catch { _viewState.value = _viewState.value.copy(error = it) }
                .collect {
                    _viewState.value =
                        _viewState.value.copy(
                            isLoading = it.isEmpty(),
                            data = _viewState.value.data.copy(popularAnimations = it)
                        )
                }
        }

        viewModelScope.launch {
            lottieAnimRepository
                .getRecentAnimations()
                .catch { _viewState.value = _viewState.value.copy(error = it) }
                .collect {
                    _viewState.value =
                        _viewState.value.copy(
                            isLoading = it.isEmpty(),
                            data = _viewState.value.data.copy(recentAnimations = it)
                        )
                }
        }

        viewModelScope.launch {
            lottieAnimRepository
                .getFeaturedAnimations()
                .catch { _viewState.value = _viewState.value.copy(error = it) }
                .collect {
                    _viewState.value =
                        _viewState.value.copy(
                            isLoading = it.isEmpty(),
                            data = _viewState.value.data.copy(featuredAnimations = it)
                        )
                }
        }
    }
}