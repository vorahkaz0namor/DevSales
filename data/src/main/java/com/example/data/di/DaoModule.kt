package com.example.data.di

import com.example.data.dao.StoreDao
import com.example.data.database.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideStoreDao(appDb: AppDb): StoreDao = appDb.storeDao()
}