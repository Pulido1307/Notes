package com.antonio.pulido.notes.view.notes.home

import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.view.core.base.viewstate.ViewState

data class HomeViewState(
    val isLoading: Boolean = false,
    val notes: List<Note> = listOf(),
): ViewState()
