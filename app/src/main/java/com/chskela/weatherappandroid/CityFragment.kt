package com.chskela.weatherappandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.chskela.weatherappandroid.adapters.HourlyAdapter
import com.chskela.weatherappandroid.databinding.FragmentCityBinding
import com.chskela.weatherappandroid.network.data.Coord
import com.chskela.weatherappandroid.viewmodels.CityViewModel
import kotlinx.coroutines.launch

class CityFragment : Fragment() {
    private val viewModel: CityViewModel by activityViewModels {
        CityViewModel.CityViewModelFactory((activity?.application as CityApplication).repository)
    }

    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
//        viewModel.cities.observe(viewLifecycleOwner, {})
        binding.hourlyList.adapter = HourlyAdapter()
        binding.dailyList.adapter = HourlyAdapter()
        Log.w("RESULT", viewModel.location.value.toString())
        viewModel.getWeatherByLocation()

    }

}