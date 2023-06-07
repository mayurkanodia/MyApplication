package com.example.heptagon.room.data.api

import androidx.room.*
import com.example.myapplication.roomdatabase.model.FavoriteData

@Database(entities = [FavoriteData::class], version = 1, exportSchema = false,)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao() : DAOAccess
}