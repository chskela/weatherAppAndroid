package com.chskela.weatherappandroid

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chskela.weatherappandroid.network.data.Hourly
//import coil.load


//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
//        }
//    }
//}
//
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Hourly>?) {
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