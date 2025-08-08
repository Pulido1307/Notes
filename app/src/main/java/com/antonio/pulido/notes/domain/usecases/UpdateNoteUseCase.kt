package com.antonio.pulido.notes.domain.usecases

import com.antonio.pulido.notes.data.mapper.toDataRequestForUpdate
import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.domain.repository.NotesRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(
        note: Note
    ) {
        notesRepository.updateNote(note.toDataRequestForUpdate())
    }
}