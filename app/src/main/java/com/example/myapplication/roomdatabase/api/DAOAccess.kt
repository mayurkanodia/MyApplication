package com.example.heptagon.room.data.api

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.roomdatabase.model.FavoriteData

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(favoriteData: FavoriteData)

    @Query("SELECT * FROM favorite WHERE Id =:id")
    fun getLoginDetails(id: Int) : FavoriteData

    @Delete
    suspend fun deleteData(loginTableModel: FavoriteData)

    @Update
    suspend fun updateData(loginTableModel: FavoriteData)

    @Query("SELECT * FROM favorite")
    fun getAll(): List<FavoriteData>

    @Query("SELECT * FROM favorite")
    fun getAllLoginDetails(): LiveData<List<FavoriteData>>

    @Insert
    suspend fun insertAll(LoginTableModels: List<FavoriteData>)

}