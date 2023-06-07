package com.example.myapplication.api

import com.example.myapplication.model.ProductList
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getProducts(): Response<ProductList> = apiService.getProducts()
}