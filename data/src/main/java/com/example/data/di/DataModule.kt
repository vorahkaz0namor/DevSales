package com.example.data.di

import com.example.data.dao.StoreDao
import com.example.data.repository.StoreRepository
import com.example.data.repository.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ DaoModule::class ])
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideStoreRepository(
        storeDao: StoreDao
    ): StoreRepository = StoreRepositoryImpl(storeDao)
}