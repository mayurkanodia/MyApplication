package com.example.myapplication.api

import com.example.myapplication.model.ProductList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("2f06b453-8375-43cf-861a-06e95a951328")
    suspend fun getProducts() : Response<ProductList>
}