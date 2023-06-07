package com.example.myapplication.di

import androidx.room.Room
import com.example.heptagon.room.data.api.FavoriteDatabase
import com.example.myapplication.roomdatabase.api.DaoHelper
import com.example.myapplication.roomdatabase.api.DaoHelperImp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomDataModelModule = module {

    single {
        Room.databaseBuilder(androidApplication(), FavoriteDatabase::class.java, "FAVORITE_DATABASE")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<FavoriteDatabase>().favoriteDao()}


   single { DaoHelperImp(get()) as DaoHelper}
}