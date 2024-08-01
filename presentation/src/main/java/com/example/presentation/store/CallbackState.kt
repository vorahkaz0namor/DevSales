package com.example.presentation.store

import com.example.data.dto.Product

data class CallbackState(
    val openEditDialog: (Product) -> Unit = {},
    val openDeleteDialog: (Product) -> Unit = {},
    val closeEditDialog: () -> Unit = {},
    val closeDeleteDialog: () -> Unit = {},
    val saveProduct: (Int) -> Unit = {},
    val deleteProduct: () -> Unit = {},
    val searchProducts: (String) -> Unit = {},
)