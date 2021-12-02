package com.chskela.weatherappandroid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chskela.weatherappandroid.databinding.FragmentCityBinding
import com.chskela.weatherappandroid.viewmodels.CityViewModel

class CityFragment : Fragment() {
    private val viewModel: CityViewModel by viewModels()
    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_city, container, false)
        binding = FragmentCityBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.hourlyList.adapter = HourlyAdapter()
        Log.d("RESULT", viewModel.weather.value.toString())
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.hourlyList.adapter = viewModel.hourlyForecastData.value?.hourly?.let {
//            HourlyAdapter(
//                it
//            )
//        }
//    }


}