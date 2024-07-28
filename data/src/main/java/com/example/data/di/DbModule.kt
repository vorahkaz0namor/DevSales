package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext
        context: Context
    ): AppDb =
        Room.databaseBuilder(
            context = context,
            klass = AppDb::class.java,
            name = "data.db"
        )
            .build()
}