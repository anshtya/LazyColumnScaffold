package com.anshtya.lazycolumnscaffold

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private var lastScrollIndex = 0

    private val _shouldScrollUp = MutableStateFlow(false)
    val shouldScrollUp = _shouldScrollUp.asStateFlow()

    fun updateScrollPosition(newScrollIndex: Int) {
        if (lastScrollIndex == newScrollIndex) return
        _shouldScrollUp.update { newScrollIndex > lastScrollIndex }
        lastScrollIndex = newScrollIndex
    }
}