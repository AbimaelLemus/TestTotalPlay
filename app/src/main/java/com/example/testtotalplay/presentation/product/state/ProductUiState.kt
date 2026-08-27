package com.example.testtotalplay.presentation.product.state

import com.example.testtotalplay.domain.model.Product

data class ProductUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val isAscending: Boolean = true
)
