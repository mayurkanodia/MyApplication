package com.example.myapplication.di

import com.example.myapplication.api.ApiHelper
import com.example.myapplication.api.ApiHelperImpl
import com.example.myapplication.api.ApiService

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {

    single { provideRetrofit( "https://run.mocky.io/v3/") }
    //single { provideRetrofit(get(),  getProperty("base_url")) }

    single { provideApiService(get()) }


    single { ApiHelperImpl(get()) as ApiHelper }
}



// Return Retrofit
private fun provideRetrofit(
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


// Return ApiService
private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

