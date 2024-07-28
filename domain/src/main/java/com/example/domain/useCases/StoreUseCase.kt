package com.example.domain.useCases

import com.example.data.Resource
import com.example.data.dto.Product
import com.example.data.repository.StoreRepository
import com.example.domain.di.IODispatcher
import com.example.domain.getQuery
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface StoreUseCase {
    suspend fun getAllProducts(): Resource<List<Product>>
    suspend fun saveProduct(product: Product): Resource<Boolean>
    suspend fun deleteProduct(product: Product): Resource<Boolean>
}

internal class StoreUseCaseImpl @Inject constructor(
    private val repository: StoreRepository,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher
): StoreUseCase {
    override suspend fun getAllProducts(): Resource<List<Product>> =
        repository.getAllProducts().getQuery(dispatcher)

    override suspend fun saveProduct(product: Product): Resource<Boolean> =
        repository.saveProduct(product).getQuery(dispatcher)

    override suspend fun deleteProduct(product: Product): Resource<Boolean> =
        repository.deleteProduct(product).getQuery(dispatcher)
}