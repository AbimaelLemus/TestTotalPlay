package com.example.testtotalplay.presentation.product.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtotalplay.data.local.repository.FakeProductRepository
import com.example.testtotalplay.domain.model.Product
import com.example.testtotalplay.presentation.product.state.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    val filteredAndSortProducts: List<Product>
        get() {
            val state = _uiState.value

            return state.products
                .filter { product ->
                    product.name.contains(
                        state.searchQuery,
                        ignoreCase = true
                    )
                }
                .let { products ->
                    if (state.isAscending) {
                        products.sortedBy { it.price }
                    } else {
                        products.sortedByDescending { it.price }
                    }
                }
        }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query
            )
        }
    }

    fun toggleSort() {
        _uiState.update {
            it.copy(
                isAscending = !it.isAscending
            )
        }
    }

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val products = FakeProductRepository().getProducts()
                Log.e("Products Success", products.size.toString())
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = products
                    )
                }
            } catch (e: Exception) {
                Log.e("Products Error", e.message.toString())
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}