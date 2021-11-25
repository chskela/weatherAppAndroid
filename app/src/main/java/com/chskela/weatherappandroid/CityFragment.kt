package com.chskela.weatherappandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chskela.weatherappandroid.databinding.FragmentCityBinding
import com.chskela.weatherappandroid.viewmodels.CityViewModel

class CityFragment : Fragment() {
    private val viewModel: CityViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_city, container, false)
        val binding = FragmentCityBinding.inflate(inflater)
        binding.viewModel = viewModel
        return binding.root
    }


}