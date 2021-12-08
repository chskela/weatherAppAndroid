package com.chskela.weatherappandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chskela.weatherappandroid.adapters.HourlyAdapter
import com.chskela.weatherappandroid.databinding.FragmentCityBinding
import com.chskela.weatherappandroid.viewmodels.CityViewModel

class CityFragment : Fragment() {
    private val viewModel: CityViewModel by viewModels()
    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.hourlyList.adapter = HourlyAdapter()
        binding.dailyList.adapter = HourlyAdapter()
        return binding.root
    }
}