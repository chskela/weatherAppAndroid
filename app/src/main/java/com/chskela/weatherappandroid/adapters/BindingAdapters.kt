package com.chskela.weatherappandroid

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chskela.weatherappandroid.adapters.HourlyAdapter
import com.chskela.weatherappandroid.viewmodels.UIData

//import coil.load


@BindingAdapter("image")
fun bindImage(imgView: ImageView, img: Int) {
    imgView.setImageResource(img)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UIData>?) {
    val adapter = recyclerView.adapter as HourlyAdapter
    adapter.submitList(data)
}

//@BindingAdapter("marsApiStatus")
//fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
//    when(status) {
//        MarsApiStatus.LOADING -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
//        }
//        MarsApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
//        }
//        MarsApiStatus.DONE -> {
//            statusImageView.visibility = View.GONE
//        }
//    }
//}