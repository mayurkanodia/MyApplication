package com.example.myapplication.ui.product

import com.example.myapplication.model.ProductList
import com.example.myapplication.roomdatabase.model.FavoriteData
import retrofit2.Response

interface ProductRepo {
    suspend fun getProducts() : Response<ProductList>
    suspend fun addFavorite(favoriteData : FavoriteData)
    suspend fun deleteFavorite(favoriteData : FavoriteData)
    suspend fun getFavorite() : List<FavoriteData>
}