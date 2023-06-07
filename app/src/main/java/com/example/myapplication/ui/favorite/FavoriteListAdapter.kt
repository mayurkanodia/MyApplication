package com.example.myapplication.ui.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemFavoriteBinding
import com.example.myapplication.model.Product
import com.example.myapplication.roomdatabase.model.FavoriteData
import java.util.ArrayList

class FavoriteListAdapter (
    private val mContext : Context,
    private val productList : ArrayList<FavoriteData>,
    private val itemClickListener : FavoriteListAdapter.ItemClickListener

) : RecyclerView.Adapter<FavoriteListAdapter.HeaderViewHolder>() {

    interface ItemClickListener{
        fun isFavoriteDeleted(favoriteProduct : FavoriteData)
        fun isItemClicked(favoriteProduct : FavoriteData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(viewHolder: HeaderViewHolder, position: Int) {
        viewHolder.run {
            bind(productList[position]) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(favoriteProducts : ArrayList<FavoriteData>) {
        productList.clear()
        productList.addAll(favoriteProducts)
        notifyDataSetChanged()
        //formFieldsArrayList.reverse()
    }

    fun deleteData(favorite : FavoriteData) {
        productList.remove(favorite)
        notifyDataSetChanged()
        //formFieldsArrayList.reverse()
    }

    inner class HeaderViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteProduct : FavoriteData) {

            binding.labelTextView.text = favoriteProduct.title
            binding.descriptionTextView.text = "$ " +favoriteProduct.price.toString()

            binding.container.setOnClickListener {
                itemClickListener.isItemClicked(favoriteProduct)
            }

            binding.delete.setOnClickListener {
                itemClickListener.isFavoriteDeleted(favoriteProduct)
            }
            mContext.let {
                Glide
                    .with(it)
                    .load(favoriteProduct.imageUrl)
                    .fitCenter()
                    .placeholder(com.google.android.material.R.drawable.mtrl_ic_error)
                    .into(binding.imageViewAvatar)
            }
        }
    }
}