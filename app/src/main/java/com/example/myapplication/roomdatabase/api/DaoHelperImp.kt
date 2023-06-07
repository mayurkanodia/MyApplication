package com.example.myapplication.roomdatabase.api

import com.example.heptagon.room.data.api.DAOAccess
import com.example.myapplication.roomdatabase.model.FavoriteData

class DaoHelperImp(private val favoriteDAOAccess : DAOAccess) : DaoHelper{
    override suspend fun insertData(favoriteData: FavoriteData) {
        favoriteDAOAccess.insertData(favoriteData)
    }

    override suspend fun deleteData(favoriteData: FavoriteData) {
        favoriteDAOAccess.deleteData(favoriteData)
    }

    override suspend fun getFavoriteProducts(): List<FavoriteData> {
       return favoriteDAOAccess.getAll()
    }
}