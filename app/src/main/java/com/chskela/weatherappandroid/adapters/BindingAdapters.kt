package com.chskela.weatherappandroid

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chskela.weatherappandroid.adapters.HourlyAdapter
import com.chskela.weatherappandroid.viewmodels.UIData
import com.chskela.weatherappandroid.viewmodels.WeatherApiStatus

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

@BindingAdapter("status")
fun bindStatus(view: View, status: WeatherApiStatus) {
    when(status) {
        WeatherApiStatus.LOADING -> {
            view.visibility = View.INVISIBLE
        }
        WeatherApiStatus.ERROR -> {
            view.visibility = View.INVISIBLE
        }
        WeatherApiStatus.DONE -> {
            view.visibility = View.VISIBLE
        }
    }
}
@BindingAdapter("spinnerStatus")
fun bindSpinnerStatus(view: View, status: WeatherApiStatus) {
    Log.w("RESULT", "status: $status")
    when(status) {
        WeatherApiStatus.LOADING -> {
            view.visibility = View.VISIBLE
        }
        WeatherApiStatus.ERROR -> {
            view.visibility = View.VISIBLE
        }
        WeatherApiStatus.DONE -> {
            view.visibility = View.INVISIBLE
        }
    }
}