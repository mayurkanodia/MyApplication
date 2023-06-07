package com.example.myapplication.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.roomdatabase.model.FavoriteData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepo : FavoriteRepo) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getFavoriteProducts() =
        liveData(Dispatchers.IO ) {
            emit(favoriteRepo.getFavoriteProducts())
        }

    fun deleteFavorite(favorite : FavoriteData){
        CoroutineScope(Dispatchers.IO).launch {
            favoriteRepo.deleteFavorite(favorite)
        }
    }

}