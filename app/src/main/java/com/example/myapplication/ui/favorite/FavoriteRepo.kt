package com.example.myapplication.ui.favorite

import com.example.myapplication.model.ProductList
import com.example.myapplication.roomdatabase.model.FavoriteData
import retrofit2.Response

interface FavoriteRepo {
    suspend fun getFavoriteProducts() : List<FavoriteData>
    suspend fun deleteFavorite(favoriteData : FavoriteData)
}