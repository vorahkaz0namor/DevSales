package com.example.presentation.store

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.onSuccess
import com.example.domain.useCases.StoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreLauncherVM @Inject constructor(
    private val useCase: StoreUseCase
): ViewModel() {
    init {
        viewModelScope.launch {
            useCase.getAllProducts().onSuccess {
                it?.let { products ->
                    Log.d("PRODUCTS", "${products.map { "$it\n" }}")
                }
            }
        }
    }
}