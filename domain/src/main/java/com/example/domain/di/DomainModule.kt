package com.example.domain.di

import com.example.data.di.DataModule
import com.example.data.repository.StoreRepository
import com.example.domain.useCases.StoreUseCase
import com.example.domain.useCases.StoreUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class,
        DispatcherModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideStoreUseCase(
        storeRepository: StoreRepository,
        @IODispatcher
        dispatcher: CoroutineDispatcher
    ): StoreUseCase = StoreUseCaseImpl(storeRepository, dispatcher)
}