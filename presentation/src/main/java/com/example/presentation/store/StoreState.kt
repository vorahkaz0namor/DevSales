package com.example.presentation.store

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.presentation.baseviews.ErrorDialog
import com.example.presentation.baseviews.ProgressBar

sealed interface StoreState {
    data object Loading: StoreState
    data object Error: StoreState
    data object Success: StoreState
}

@Composable
fun StoreState.StateHandler(
    onDismissRequest: () -> Unit = {},
    composeAfterSuccess: @Composable () -> Unit
) {
    Column {
        when (this@StateHandler) {
            is StoreState.Loading -> ProgressBar()
            is StoreState.Error -> ErrorDialog(onDismissRequest = onDismissRequest)
            is StoreState.Success -> composeAfterSuccess()
        }
    }
}