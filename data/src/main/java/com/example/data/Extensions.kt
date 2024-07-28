package com.example.data

import java.io.IOException

sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure(val error: IOException) : Resource<Nothing>()
}

internal suspend fun <T> handleDAOCall(
    daoCall: suspend () -> T?
): Resource<T> =
    try {
        Resource.Success(data = daoCall())
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Failure(IOException())
    }