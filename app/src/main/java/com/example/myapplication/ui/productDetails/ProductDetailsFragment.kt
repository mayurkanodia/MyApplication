package com.example.myapplication.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentProductDetailsBinding
import com.example.myapplication.roomdatabase.model.FavoriteData
import com.google.gson.Gson

class ProductDetails : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val jsonFavorite = arguments?.getString("favorite")
        if (jsonFavorite != null){
           val favorite : FavoriteData = Gson().fromJson(jsonFavorite, FavoriteData::class.java)
            favorite?.let {favorite ->
                binding.labelTextView.text = favorite.title
                binding.descriptionTextView.text = "Favorite : " +favorite.isFavorite
                binding.priceTextView.text = "Price : $ " +favorite.price.toString()
                binding.ratingBar.rating = favorite.rating.toFloat()
                activity?.let {
                    Glide
                        .with(it)
                        .load(favorite.imageUrl)
                        .fitCenter()
                        .placeholder(com.google.android.material.R.drawable.mtrl_ic_error)
                        .into(binding.imageViewAvatar)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}