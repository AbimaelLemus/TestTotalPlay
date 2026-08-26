package com.example.testtotalplay.presentation.product.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testtotalplay.domain.model.Product

@Composable
fun ProductRow(
    product: Product
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = product.name
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "$${product.price}"
        )
    }
}