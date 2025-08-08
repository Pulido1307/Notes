package com.antonio.pulido.notes.domain.usecases

import com.antonio.pulido.notes.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
){
    suspend operator fun invoke(
        id: Int
    ){
        notesRepository.deleteNote(id)
    }
}