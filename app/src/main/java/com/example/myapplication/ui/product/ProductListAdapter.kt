package com.example.myapplication.ui.product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemHeaderLayoutBinding
import com.example.myapplication.model.Product
import java.util.ArrayList
import com.bumptech.glide.Glide

class ProductListAdapter(
    private val mContext : Context,
    private val productList :  ArrayList<Product>,
    private val itemClickListener : ItemClickListener
) : RecyclerView.Adapter<ProductListAdapter.HeaderViewHolder>() {


    interface ItemClickListener{
        fun isFavoriteClicked(product : Product , isChecked : Boolean)
        fun isItemClicked(product : Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(
            ItemHeaderLayoutBinding.inflate(
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
    fun addData(products : ArrayList<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
        //formFieldsArrayList.reverse()
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {

            //binding.product = product
            binding.labelTextView.text = product.title
            binding.descriptionTextView.text = "$ " +product.price[0].value.toString()

            binding.container.setOnClickListener {
                itemClickListener.isItemClicked(product)
            }

            binding.isFavorite.isChecked = product.isFavorite

            binding.isFavorite.setOnClickListener {
                itemClickListener.isFavoriteClicked(product,binding.isFavorite.isChecked)
            }

            mContext.let {
                Glide
                    .with(it)
                    .load(product.imageURL)
                    .fitCenter()
                    .placeholder(com.google.android.material.R.drawable.mtrl_ic_error)
                    .into(binding.imageViewAvatar)
            }
        }
    }
}