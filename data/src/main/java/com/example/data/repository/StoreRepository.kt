package com.example.data.repository

import com.example.data.Resource
import com.example.data.dao.StoreDao
import com.example.data.dto.Product
import com.example.data.entity.ProductEntity
import com.example.data.handleDAOCall
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

interface StoreRepository {
    suspend fun getAllProducts(): Resource<Flow<List<Product>>>
    suspend fun saveProduct(product: Product): Resource<Boolean>
    suspend fun deleteProduct(product: Product): Resource<Boolean>
}

internal class StoreRepositoryImpl @Inject constructor(
    private val storeDao: StoreDao
): StoreRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllProducts(): Resource<Flow<List<Product>>> =
        handleDAOCall {
            storeDao.getAllProducts().mapLatest { products ->
                products.map { it.toDto() }
            }
        }

    override suspend fun saveProduct(product: Product): Resource<Boolean> =
        handleDAOCall {
            storeDao.saveProduct(ProductEntity.fromDto(product))
            true
        }

    override suspend fun deleteProduct(product: Product): Resource<Boolean> =
        handleDAOCall {
            storeDao.deleteProduct(ProductEntity.fromDto(product))
            true
        }
}