package com.antonio.pulido.notes.view.notes.detail

import android.graphics.Bitmap
import android.net.Uri

sealed interface DetailViewEvent {
    data object ShowDrawingDialog : DetailViewEvent
    data object HiddenDrawingDialog : DetailViewEvent
    data object SaveNote : DetailViewEvent
    data class GetNoteById(val id: Int) : DetailViewEvent
    data class OnChangeTitle(val title: String) : DetailViewEvent
    data class OnChangeContent(val content: String) : DetailViewEvent
    data class OnChangeImageSelected(val image: Uri) : DetailViewEvent
    data class OnChangeDrawingFinished(val bitmap: Bitmap) : DetailViewEvent
}