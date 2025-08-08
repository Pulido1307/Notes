package com.antonio.pulido.notes.view.notes.detail

import android.net.Uri

sealed interface DetailViewEvent {
    data object AddNote : DetailViewEvent
    data class GetNoteById(val id: Int) : DetailViewEvent
    data class OnChangeTitle(val title: String) : DetailViewEvent
    data class OnChangeContent(val content: String) : DetailViewEvent
    data class OnChangeImageSelected(val image: Uri) : DetailViewEvent
}