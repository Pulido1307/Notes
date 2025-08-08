package com.antonio.pulido.notes.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antonio.pulido.notes.data.local.dao.NoteDao
import com.antonio.pulido.notes.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class DbRoom : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        fun newInstance(context: Context): DbRoom {
            return Room.databaseBuilder(context, DbRoom::class.java, "DbNotes")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}