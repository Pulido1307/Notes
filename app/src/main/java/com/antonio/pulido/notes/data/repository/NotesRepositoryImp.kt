package com.antonio.pulido.notes.data.repository

import com.antonio.pulido.notes.data.local.dao.NoteDao
import com.antonio.pulido.notes.data.model.NoteEntity
import com.antonio.pulido.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {
    override suspend fun addNote(noteEntity: NoteEntity) {
        noteDao.addNote(noteEntity)
    }

    override fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getNotes()
    }

    override suspend fun getNote(id: Int): NoteEntity {
        return noteDao.getNote(id)
    }

    override suspend fun deleteNote(id: Int) {
        return noteDao.deleteNote(id)
    }

    override suspend fun updateNote(noteEntity: NoteEntity) {
        return noteDao.updateNote(noteEntity)
    }
}
