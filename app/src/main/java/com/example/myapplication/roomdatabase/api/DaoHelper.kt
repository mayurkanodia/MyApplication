package com.example.myapplication.roomdatabase.api

import com.example.myapplication.roomdatabase.model.FavoriteData

interface DaoHelper {
    suspend fun insertData(favoriteData: FavoriteData)
    suspend fun deleteData(favoriteData: FavoriteData)
    suspend fun getFavoriteProducts(): List<FavoriteData>
}