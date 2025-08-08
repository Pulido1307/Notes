package com.antonio.pulido.notes.domain.repository

import com.antonio.pulido.notes.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<NoteEntity>>
}