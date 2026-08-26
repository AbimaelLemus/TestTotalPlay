package com.example.testtotalplay.domain.repository

import com.example.testtotalplay.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}