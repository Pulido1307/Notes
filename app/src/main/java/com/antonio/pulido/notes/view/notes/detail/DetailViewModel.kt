package com.antonio.pulido.notes.view.notes.detail

import android.app.Application
import android.graphics.Bitmap
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.notes.R
import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.domain.usecases.AddNoteUseCase
import com.antonio.pulido.notes.domain.usecases.GetNoteByIdUseCase
import com.antonio.pulido.notes.domain.usecases.UpdateNoteUseCase
import com.antonio.pulido.notes.utils.saveBitmapAndGetUri
import com.antonio.pulido.notes.utils.saveImageFromUri
import com.antonio.pulido.notes.view.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : BaseViewModel(application) {
    init {
        initViewState(DetailViewState())
    }

    fun onEvent(event: DetailViewEvent) {
        when (event) {
            is DetailViewEvent.OnChangeContent -> updateViewState(
                currentViewState<DetailViewState>().copy(
                    content = event.content,
                )
            )

            is DetailViewEvent.OnChangeImageSelected -> updateViewState(
                currentViewState<DetailViewState>().copy(
                    imagePath = event.image,
                    imagePathRecovery = null
                )
            )

            is DetailViewEvent.OnChangeTitle -> updateViewState(
                currentViewState<DetailViewState>().copy(
                    title = event.title,
                )
            )

            is DetailViewEvent.GetNoteById -> getNoteById(event.id)
            is DetailViewEvent.OnChangeDrawingFinished -> onChangeDrawingFinished(event.bitmap)

            DetailViewEvent.SaveNote -> saveNote()
            DetailViewEvent.HiddenDrawingDialog -> setStatusShowDialog(false)
            DetailViewEvent.ShowDrawingDialog -> setStatusShowDialog(true)
        }
    }

    private fun onChangeDrawingFinished(bitmap: Bitmap) = viewModelScope.launch {
        updateViewState(
            currentViewState<DetailViewState>().copy(
                imagePath = saveBitmapAndGetUri(getApplication(), bitmap),
                imagePathRecovery = null,
                showDrawingDialog = false
            )
        )
    }

    private fun setStatusShowDialog(isShow: Boolean) {
        updateViewState(
            currentViewState<DetailViewState>().copy(
                showDrawingDialog = isShow
            )
        )
    }

    private fun getNoteById(id: Int) = viewModelScope.launch {
        val note = getNoteByIdUseCase(
            id = id
        )

        updateViewState(
            currentViewState<DetailViewState>().copy(
                title = note.title,
                content = note.content,
                imagePathRecovery = note.imagePath,
                id = id,
                titleScreen = R.string.edit_note_screen_title
            )
        )
    }

    private fun saveNote() {
        val state = currentViewState<DetailViewState>()

        if (isValid()) {
            if (state.id == null) {
                addNote()
            } else {
                updateNote()
            }
        }
    }

    private fun updateNote() = viewModelScope.launch {
        val state = currentViewState<DetailViewState>()
        val imagePath = if (state.imagePath == null) state.imagePathRecovery else saveImageFromUri(
            getApplication(),
            state.imagePath
        )

        updateNoteUseCase(
            Note(
                id = state.id ?: 0,
                title = state.title,
                content = state.content,
                imagePath = imagePath
            )
        )
        updateViewState(
            currentViewState<DetailViewState>().copy(
                successAddOrEdit = true
            )
        )
    }

    private fun addNote() = viewModelScope.launch {

        val state = currentViewState<DetailViewState>()
        val imagePath = state.imagePath?.let { saveImageFromUri(getApplication(), it) }
        addNoteUseCase(
            Note(
                title = state.title,
                content = state.content,
                imagePath = imagePath
            )
        )
        updateViewState(
            currentViewState<DetailViewState>().copy(
                successAddOrEdit = true
            )
        )
    }

    private fun isValid(): Boolean {
        val state = currentViewState<DetailViewState>()
        return state.title.isNotEmpty() || state.content.isNotEmpty() || state.imagePath != null
    }
}