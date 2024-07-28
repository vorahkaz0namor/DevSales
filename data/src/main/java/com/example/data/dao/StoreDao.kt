package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.data.entity.ProductEntity

@Dao
interface StoreDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = REPLACE)
    suspend fun saveProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)
}