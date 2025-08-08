package com.antonio.pulido.notes.view.core.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.antonio.pulido.notes.view.core.base.viewstate.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var _viewState: MutableStateFlow<ViewState>
    protected lateinit var viewState: StateFlow<ViewState>

    protected fun initViewState(state: ViewState) {
        _viewState = MutableStateFlow(state)
        viewState = _viewState.asStateFlow()
    }

    protected fun updateViewState(newViewState: ViewState) {
        _viewState.update { newViewState }
    }

    protected fun <T> currentViewState(): T = _viewState.value as T

    fun <T> getState() = viewState as StateFlow<T>
}