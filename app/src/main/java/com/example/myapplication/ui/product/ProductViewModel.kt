package com.example.myapplication.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.roomdatabase.model.FavoriteData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepo: ProductRepo) : ViewModel() {

    private val _favoritesList = MutableLiveData<List<FavoriteData>>()
    val favoritesList: LiveData<List<FavoriteData>> = _favoritesList


    fun getProducts() =
        liveData(Dispatchers.IO ) {
            emit(productRepo.getProducts())
        }

    fun addFavorite(favorite : FavoriteData){
        CoroutineScope(Dispatchers.IO).launch {
            productRepo.addFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite : FavoriteData){
        CoroutineScope(Dispatchers.IO).launch {
            productRepo.deleteFavorite(favorite)
        }
    }


    fun getFavorites() =
        liveData(Dispatchers.IO ) {
            emit( productRepo.getFavorite())
        }

   /* fun getFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            productRepo.getFavorite()
        }
    }*/

}