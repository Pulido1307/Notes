package com.antonio.pulido.notes.view.notes.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.notes.domain.usecases.GetNotesUseCase
import com.antonio.pulido.notes.view.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val getNotesUseCase: GetNotesUseCase
) : BaseViewModel(application) {
    init {
        initViewState(HomeViewState())
        getNotes()
    }

    private fun getNotes() = viewModelScope.launch {
        getNotesUseCase().collect { notes ->
            updateViewState(
                currentViewState<HomeViewState>().copy(
                    notes = notes
                )
            )
        }
    }
}