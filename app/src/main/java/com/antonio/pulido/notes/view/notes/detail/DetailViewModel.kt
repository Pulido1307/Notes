package com.antonio.pulido.notes.view.notes.detail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.domain.usecases.AddNoteUseCase
import com.antonio.pulido.notes.domain.usecases.GetNoteByIdUseCase
import com.antonio.pulido.notes.utils.saveImageFromUri
import com.antonio.pulido.notes.view.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val addNoteUseCase: AddNoteUseCase,
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
                    imagePath = event.image
                )
            )

            is DetailViewEvent.OnChangeTitle -> updateViewState(
                currentViewState<DetailViewState>().copy(
                    title = event.title,
                )
            )

            is DetailViewEvent.GetNoteById -> getNoteById(event.id)
            DetailViewEvent.AddNote -> addNote()
        }
    }

    private fun getNoteById(id: Int) = viewModelScope.launch {
        val note = getNoteByIdUseCase(
            id = id
        )

        updateViewState(
            currentViewState<DetailViewState>().copy(
                title = note.title,
                content = note.content,
                imagePathRecovery = note.imagePath
            )
        )
    }

    private fun addNote() = viewModelScope.launch {
        if (isValid()) {
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
        } else {
            Toast.makeText(
                getApplication(),
                "Favor de llenar alguno de los datos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isValid(): Boolean {
        val state = currentViewState<DetailViewState>()
        return state.title.isNotEmpty() || state.content.isNotEmpty() || state.imagePath != null
    }
}