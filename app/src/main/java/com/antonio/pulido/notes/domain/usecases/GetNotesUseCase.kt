package com.antonio.pulido.notes.domain.usecases

import com.antonio.pulido.notes.data.mapper.toDomain
import com.antonio.pulido.notes.domain.model.Note
import com.antonio.pulido.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return notesRepository.getNotes().map { notes ->
            notes.map {
                it.toDomain()
            }
        }
    }
}