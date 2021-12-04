package com.chskela.weatherappandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chskela.weatherappandroid.databinding.HourlyItemBinding
import com.chskela.weatherappandroid.viewmodels.UIData


class HourlyAdapter :
    ListAdapter<UIData, HourlyAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<UIData>() {

        override fun areItemsTheSame(oldItem: UIData, newItem: UIData): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: UIData, newItem: UIData): Boolean {
            return oldItem.temp == newItem.temp
        }
    }


    class ViewHolder(private val binding: HourlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UIData) {
            binding.item = data
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