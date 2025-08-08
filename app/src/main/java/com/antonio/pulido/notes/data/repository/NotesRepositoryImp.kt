package com.antonio.pulido.notes.data.repository

import com.antonio.pulido.notes.data.local.dao.NoteDao
import com.antonio.pulido.notes.data.model.NoteEntity
import com.antonio.pulido.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {
    override fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getNotes()
    }
}
