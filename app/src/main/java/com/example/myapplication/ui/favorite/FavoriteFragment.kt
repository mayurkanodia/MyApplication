package com.example.myapplication.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFavoriteListBinding
import com.example.myapplication.extensions.showLongToast
import com.example.myapplication.roomdatabase.model.FavoriteData
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class FavoriteFragment : Fragment() , FavoriteListAdapter.ItemClickListener {

    private lateinit var binding: FragmentFavoriteListBinding

    private val favoriteViewModel : FavoriteViewModel by viewModel()

    private lateinit var favoriteListAdapter: FavoriteListAdapter

    private lateinit var appContext: Context

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return  binding.root

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
            favoriteListAdapter = FavoriteListAdapter(requireContext(), arrayListOf(),this@FavoriteFragment)
            binding.recyclerView.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = favoriteListAdapter
            }
            binding.swipeRefreshLayout.setOnRefreshListener {  getProductListObserver() }
        }

    }

    private fun getProductListObserver(){
            showLoading(true)
            favoriteViewModel.getFavoriteProducts().observe(viewLifecycleOwner) {
                if(it.isNotEmpty()){
                    favoriteListAdapter.addData(it as ArrayList<FavoriteData>)
                }else{
                    appContext.showLongToast("No Favorite Product")
                }

                showLoading(false)
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

    override fun isFavoriteDeleted(favoriteProduct: FavoriteData) {
        favoriteViewModel.deleteFavorite(favoriteProduct)
        favoriteListAdapter.deleteData(favoriteProduct)
        appContext.showLongToast("Favorite Removed")
    }

    override fun isItemClicked(favoriteProduct: FavoriteData) {
        val bundle = Bundle()
        bundle.putString("favorite", Gson().toJson(favoriteProduct))
        navController.navigate(R.id.action_navigation_favorite_list_to_navigation_product_details,bundle)
    }
}