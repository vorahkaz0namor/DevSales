package com.example.presentation.store

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
import kotlinx.coroutines.flow.collectLatest
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
    private val filterRequest = MutableStateFlow("")

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

    fun saveProduct(newAmount: Int) {
        viewModelScope.launch {
            activeProduct.value?.let { product ->
                useCase.saveProduct(
                    listOf(product.copy(amount = newAmount))
                )
            }
            closeEditDialog()
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

    fun deleteProduct() {
        viewModelScope.launch {
            activeProduct.value?.let { product ->
                useCase.deleteProduct(product)
            }
            closeDeleteDialog()
        }
    }

    fun searchProducts(text: String) {
        filterRequest.value = text
    }

    private fun loadProducts() {
        viewModelScope.launch {
            filterRequest.collectLatest {
                useCase.getAllProducts().fetchingData { productsFlow ->
                    productsFlow.mapLatest { productsFromFlow ->
                        if (productsFromFlow.isEmpty()) {
                            // TODO: Hardcode to create products when DB is empty
                            useCase.saveProduct(Product.initialProductList)
                        } else {
                            _products.value =
                                /**
                                 * Filter products depending search request
                                 */
                                if (filterRequest.value.isNotBlank())
                                    productsFromFlow.filter {
                                        it.name.contains(other = filterRequest.value, ignoreCase = true)
                                    }
                                else
                                    productsFromFlow
                        }
                    }
                        .stateIn(this)
                }
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