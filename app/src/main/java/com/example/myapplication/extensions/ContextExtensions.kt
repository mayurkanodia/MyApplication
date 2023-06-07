package com.example.myapplication.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@SuppressLint("MissingPermission")
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as? ConnectivityManager
    val activeNetworkInfo = connectivityManager?.activeNetworkInfo

    return activeNetworkInfo?.isConnectedOrConnecting ?: run { false }
}

/*
@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url : String){
    // set image
    // Glide.with(this.context).load(url).into(this)
}
*/


fun Context.showShortToast(toastMsg: String?) = toastMsg?.let {
    Toast.makeText(this.applicationContext, it, Toast.LENGTH_SHORT).show()
}

fun Context.showShortToast(@StringRes toastMsg: Int) =
    Toast.makeText(this.applicationContext, toastMsg, Toast.LENGTH_SHORT).show()

fun Context.showLongToast(toastMsg: String?) = toastMsg?.let {
    Toast.makeText(this.applicationContext, it, Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(@StringRes toastMsg: Int) =
    Toast.makeText(this.applicationContext, toastMsg, Toast.LENGTH_LONG).show()

