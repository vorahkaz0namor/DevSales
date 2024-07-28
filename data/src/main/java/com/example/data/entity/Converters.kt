package com.example.data.entity

import androidx.room.TypeConverter

class Converters {
    private val separator = ","

    private fun <T> listToString(list: List<T>?) =
        buildString {
            list?.map { append(it, separator) }
        }

    @TypeConverter
    fun stringListToString(list: List<String>?): String =
        listToString(list)

    @TypeConverter
    fun stringToStringList(string: String?): List<String>? =
        string?.split(separator)?.filter { it.isNotEmpty() }
}