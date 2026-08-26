package com.example.testtotalplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testtotalplay.presentation.product.screen.ProductScreen
import com.example.testtotalplay.presentation.product.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductScreen(viewModel = ProductViewModel())
        }
    }
}