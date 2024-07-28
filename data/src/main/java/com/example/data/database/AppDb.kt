package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.StoreDao
import com.example.data.entity.Converters
import com.example.data.entity.ProductEntity

@Database(
    entities = [ ProductEntity::class ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}