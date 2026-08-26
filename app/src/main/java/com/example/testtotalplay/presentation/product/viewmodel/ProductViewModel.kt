package com.example.testtotalplay.presentation.product.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testtotalplay.presentation.product.state.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ProductViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {

    }


}