package com.example.myapplication.di

import com.example.myapplication.ui.favorite.FavoriteRepo
import com.example.myapplication.ui.favorite.FavoriteRepoImp
import com.example.myapplication.ui.product.ProductRepo
import com.example.myapplication.ui.product.ProductRepoImp
import org.koin.dsl.module

val repoModule = module {
    single {
        ProductRepoImp(get(),get()) as ProductRepo
    }
    single {
        FavoriteRepoImp(get()) as FavoriteRepo
    }
}