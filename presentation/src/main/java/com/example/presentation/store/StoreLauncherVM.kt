package com.example.presentation.store

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Resource
import com.example.data.dto.Product
import com.example.domain.onFailure
import com.example.domain.onSuccess
import com.example.domain.useCases.StoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class StoreLauncherVM @Inject constructor(
    private val useCase: StoreUseCase
): ViewModel() {
    private val _storeState = MutableStateFlow<StoreState>(StoreState.Loading)
    val storeState: StateFlow<StoreState>
        get() = _storeState.asStateFlow()
    private val _products = MutableStateFlow(emptyList<Product>())
    val products: StateFlow<List<Product>>
        get() = _products.asStateFlow()
    private val _openEditDialog = MutableStateFlow(false)
    val openEditDialog: StateFlow<Boolean>
        get() = _openEditDialog.asStateFlow()
    private val _openDeleteDialog = MutableStateFlow(false)
    val openDeleteDialog: StateFlow<Boolean>
        get() = _openDeleteDialog.asStateFlow()
    private val _activeProduct = MutableStateFlow<Product?>(null)
    val activeProduct: StateFlow<Product?>
        get() = _activeProduct.asStateFlow()

    init {
        loadProducts()
    }

    fun openEditDialog(product: Product) {
        _openEditDialog.value = true
        _activeProduct.value = product
    }

    fun closeEditDialog() {
        _openEditDialog.value = false
        _activeProduct.value = null
    }

    fun saveProduct(product: Product) {
        viewModelScope.launch {
            useCase.saveProduct(product)
        }
    }

    fun openDeleteDialog(product: Product) {
        _openDeleteDialog.value = true
        _activeProduct.value = product
    }

    fun closeDeleteDialog() {
        _openDeleteDialog.value = false
        _activeProduct.value = null
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            useCase.deleteProduct(product)
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            useCase.getAllProducts().fetchingData { productsFlow ->
                productsFlow.mapLatest { productsFromFlow ->
                    _products.value = productsFromFlow
                    Log.d("PRODUCTS HAS LOADED", "${productsFromFlow.size}")
                }
                    .stateIn(this)
            }
        }
    }

    /**
     * Fetching incoming data
     */
    private inline fun <T> Resource<T>
            .fetchingData(action: (value: T) -> Unit) {
        _storeState.value = StoreState.Loading
        onSuccess {
            action(it)
            _storeState.value = StoreState.Success
        }
            .onFailure {
                _storeState.value = StoreState.Error
            }
    }
}