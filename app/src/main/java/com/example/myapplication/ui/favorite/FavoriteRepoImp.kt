package com.example.myapplication.ui.favorite

import com.example.myapplication.api.ApiHelper
import com.example.myapplication.model.ProductList
import com.example.myapplication.roomdatabase.api.DaoHelper
import com.example.myapplication.roomdatabase.model.FavoriteData
import retrofit2.Response

class FavoriteRepoImp(private val daoHelper: DaoHelper) : FavoriteRepo {
    override suspend fun getFavoriteProducts(): List<FavoriteData> {
       return daoHelper.getFavoriteProducts()
    }

    override suspend fun deleteFavorite(favoriteData: FavoriteData) {
        daoHelper.deleteData(favoriteData)
    }

}