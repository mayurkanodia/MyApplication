package com.example.myapplication.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductListBinding
import com.example.myapplication.extensions.isNetworkAvailable
import com.example.myapplication.extensions.showLongToast
import com.example.myapplication.model.Product
import com.example.myapplication.roomdatabase.model.FavoriteData
import com.google.gson.Gson

class ProductFragment : Fragment() , ProductListAdapter.ItemClickListener {

    private lateinit var binding: FragmentProductListBinding

    private val productViewModel : ProductViewModel by viewModel()

    private lateinit var productListAdapter: ProductListAdapter

    private lateinit var appContext: Context

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appContext = context.applicationContext
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        getProductListObserver()
    }


    private fun initRecyclerView(view: View) {
        navController = view.findNavController()
        binding.run {
            productListAdapter = ProductListAdapter(requireContext(), arrayListOf(),this@ProductFragment)
            binding.recyclerView.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = productListAdapter
            }
            binding.swipeRefreshLayout.setOnRefreshListener {  getProductListObserver() }
        }

    }

    private fun getProductListObserver(){
        if (appContext.isNetworkAvailable()) {
            showLoading(true)
           val favoriteList : ArrayList<FavoriteData> = ArrayList()
           productViewModel.getFavorites().observe(viewLifecycleOwner){
               favoriteList.addAll(it)
           }
            productViewModel.getProducts().observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                    showLoading(false)
                    it.body()?.let { products ->
                        if (favoriteList.isNotEmpty()){
                            for (favorite in favoriteList){
                                for (product in products.products){
                                    if(product.id == favorite.id.toString()){
                                     product.isFavorite =true
                                    }
                                }
                            }
                        }
                        productListAdapter.addData(products.products as ArrayList<Product>)
                    }
                } else {
                    showLoading(false)
                    appContext.showLongToast(it.errorBody().toString())
                }
            }
        } else {
            showLoading(false)
            appContext.showLongToast(R.string.no_network_connection)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.recyclerView.visibility = View.GONE
        }else{
            binding.recyclerView.visibility = View.VISIBLE
        }
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun isFavoriteClicked(product: Product, isChecked: Boolean) {
        if (isChecked){
            productViewModel.addFavorite(FavoriteData(product.title,
                product.price[0].value.toString(),
                product.imageURL,
                true,product.ratingCount,product.id.toInt()))
            appContext.showLongToast("Favorite Added")
        }else{
            productViewModel.deleteFavorite(FavoriteData(product.title,
                product.price[0].value.toString(),
                product.imageURL,
                false,product.ratingCount,product.id.toInt()))
            appContext.showLongToast("Favorite Removed")
        }
    }

    override fun isItemClicked(product: Product) {
        val bundle = Bundle()
        bundle.putString("favorite",Gson().toJson(FavoriteData(
            product.title,
            product.price[0].value.toString(),
            product.imageURL,
            product.isFavorite,
            product.ratingCount,
            product.id.toInt())))
        navController.navigate(R.id.action_navigation_product_list_to_navigation_product_details,bundle)
    }
}