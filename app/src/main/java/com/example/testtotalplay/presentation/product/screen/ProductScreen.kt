package com.example.testtotalplay.presentation.product.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.testtotalplay.presentation.product.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(viewModel: ProductViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Products")
                }
            )
        }
    ) { padding ->
        //agregar condiciones para el error
        when {
            uiState.isLoading -> {
                Column() {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                Column() {
                    Text(text = uiState.error!!)
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(uiState.products) { product ->
                        ProductRow(product = product)
                    }
                }
            }

        }
    }
}