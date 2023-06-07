package com.example.myapplication.ui.product

import com.example.myapplication.api.ApiHelper
import com.example.myapplication.model.ProductList
import com.example.myapplication.roomdatabase.api.DaoHelper
import com.example.myapplication.roomdatabase.model.FavoriteData
import retrofit2.Response

class ProductRepoImp(private val apiHelper: ApiHelper , private val daoHelper: DaoHelper) : ProductRepo {
    override suspend fun getProducts(): Response<ProductList> {
        return apiHelper.getProducts()
    }

    override suspend fun addFavorite(favoriteData: FavoriteData) {
        daoHelper.insertData(favoriteData)
    }

    override suspend fun deleteFavorite(favoriteData: FavoriteData) {
        daoHelper.deleteData(favoriteData)
    }

    override suspend fun getFavorite(): List<FavoriteData> {
        return daoHelper.getFavoriteProducts()
    }
}