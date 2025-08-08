package com.antonio.pulido.notes.view.notes.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.notes.domain.usecases.DeleteUseCase
import com.antonio.pulido.notes.domain.usecases.GetNotesUseCase
import com.antonio.pulido.notes.view.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteUseCase: DeleteUseCase
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

    fun onEvent(event: HomeViewEvent) {
        when (event) {
            HomeViewEvent.HiddenDeleteDialog -> updateViewState(
                currentViewState<HomeViewState>().copy(
                    showDeleteDialog = false,
                    idSelected = null
                )
            )

            is HomeViewEvent.ShowDeleteDialog -> updateViewState(
                currentViewState<HomeViewState>().copy(
                    showDeleteDialog = true,
                    idSelected = event.id
                )
            )

            HomeViewEvent.DeleteNote -> deleteNote()
        }
    }

    private fun deleteNote() = viewModelScope.launch {
        val id = currentViewState<HomeViewState>().idSelected
        deleteUseCase(
            id = id ?: 0
        )
        updateViewState(
            currentViewState<HomeViewState>().copy(
                showDeleteDialog = false,
                idSelected = null
            )
        )
    }
}