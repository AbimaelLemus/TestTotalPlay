package com.example.testtotalplay.domain.usecase

import com.example.testtotalplay.domain.model.Product
import com.example.testtotalplay.domain.repository.ProductRepository
import javax.inject.Inject


class GetProductUseCase (private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}