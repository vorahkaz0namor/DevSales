package com.example.presentation.store

import com.example.data.dto.Product

data class CallbackState(
    val openEditDialog: (Product) -> Unit = {},
    val openDeleteDialog: (Product) -> Unit = {},
    val closeEditDialog: () -> Unit = {},
    val closeDeleteDialog: () -> Unit = {},
    val saveProduct: (Product) -> Unit = {},
    val deleteProduct: (Product) -> Unit = {}
)