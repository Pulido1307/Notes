package com.antonio.pulido.notes.data.mapper

import com.antonio.pulido.notes.data.model.NoteEntity
import com.antonio.pulido.notes.domain.model.Note

fun NoteEntity.toDomain() = Note(
    id = this.id,
    title = this.title,
    content = this.content,
    imagePath = this.imagePath
)