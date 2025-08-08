package com.antonio.pulido.notes.domain.usecases

import com.antonio.pulido.notes.data.mapper.toDataRequest
import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.domain.repository.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(
        note: Note
    ) {
        return notesRepository.addNote(note.toDataRequest())
    }
}