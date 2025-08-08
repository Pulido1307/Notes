package com.antonio.pulido.notes.domain.repository

import com.antonio.pulido.notes.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun addNote(noteEntity: NoteEntity)
    fun getNotes(): Flow<List<NoteEntity>>
    suspend fun getNote(id: Int): NoteEntity
    suspend fun deleteNote(id: Int)
    suspend fun updateNote(noteEntity: NoteEntity)
}