package com.example.myapplication.di

import com.example.myapplication.ui.favorite.FavoriteViewModel
import com.example.myapplication.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ProductViewModel(get())
    }

    viewModel {
        FavoriteViewModel(get())
    }

}
