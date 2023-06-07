package com.example.myapplication.api

import com.example.myapplication.model.ProductList
import retrofit2.Response


interface ApiHelper {

    suspend fun getProducts(): Response<ProductList>

}