package com.antonio.pulido.notes.di

import android.content.Context
import com.antonio.pulido.notes.data.local.db.DbRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ): DbRoom {
        return DbRoom.newInstance(context)
    }
}