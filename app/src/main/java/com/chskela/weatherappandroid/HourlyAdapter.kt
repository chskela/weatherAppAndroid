package com.chskela.weatherappandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chskela.weatherappandroid.databinding.HourlyItemBinding
import com.chskela.weatherappandroid.network.data.Hourly


class HourlyAdapter :
    ListAdapter<Hourly, HourlyAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Hourly>() {

        override fun areItemsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
            return oldItem.temp == newItem.temp
        }
    }


    class ViewHolder(val binding: HourlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hourly: Hourly) {
            binding.item = hourly
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HourlyItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}