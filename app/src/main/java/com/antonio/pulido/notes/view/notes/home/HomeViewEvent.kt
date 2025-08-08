package com.antonio.pulido.notes.view.notes.home

sealed interface HomeViewEvent {
    data object DeleteNote: HomeViewEvent
    data object HiddenDeleteDialog : HomeViewEvent
    data class ShowDeleteDialog(val id: Int) : HomeViewEvent
}