package com.example.testtotalplay.data.local.repository

import com.example.testtotalplay.domain.model.Product
import com.example.testtotalplay.domain.repository.ProductRepository
import kotlinx.coroutines.delay

class FakeProductRepository : ProductRepository {

    override suspend fun getProducts(): List<Product> {

        delay(1000)

        /*if (System.currentTimeMillis() % 2L == 0L) {
            error("Network error")
        }*/

        return listOf(
            Product("1", "iPhone", 999.0),
            Product("2", "Samsung", 899.0),
            Product("3", "Xiaomi", 499.0),
            Product("4", "Motorola", 299.0),
        )
    }
}