package com.antonio.pulido.notes.di

import com.antonio.pulido.notes.data.local.db.DbRoom
import com.antonio.pulido.notes.data.repository.NotesRepositoryImp
import com.antonio.pulido.notes.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesNoteRepository(
        dbRoom: DbRoom
    ): NotesRepository {
        return NotesRepositoryImp(dbRoom.noteDao())
    }
}