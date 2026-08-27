package com.example.testtotalplay.presentation.product.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = uiState.error!!)
                    Button(
                        onClick = {
                            viewModel.getProducts()
                        }) {
                        Text(text = "Reintentar")
                    }
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = {
                            viewModel.onSearchQueryChange(it)
                        },
                        label = {
                            Text(text = "Buscar producto")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )


                    Button(
                        onClick = {
                            viewModel.toggleSort()
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(16.dp)
                    ) {
                        Text(
                            text =
                            if (uiState.isAscending) {
                                "Precio ↑"
                            } else {
                                "Precio ↓"
                            }
                        )
                    }

                    val products = viewModel.filteredAndSortProducts

                    if (products.isEmpty() && uiState.searchQuery.isNotBlank()) {
                        Text(
                            text = "No se encontraron productos",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            items(products) { product ->
                                ProductRow(product = product)
                            }
                        }
                    }
                }
            }

        }
    }
}