package com.antonio.pulido.notes.domain.usecases

import com.antonio.pulido.notes.data.mapper.toDomain
import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.domain.repository.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(
        id: Int
    ): Note {
        return notesRepository.getNote(id).toDomain()
    }
}