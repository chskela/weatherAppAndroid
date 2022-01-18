package com.chskela.weatherappandroid


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.chskela.weatherappandroid.network.data.Coord
import com.chskela.weatherappandroid.viewmodels.CityViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private val viewModel: CityViewModel by viewModels {
        CityViewModel.CityViewModelFactory((application as CityApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coord = intent.getSerializableExtra(StartActivity.LOCATION) as Coord?

        Log.w("RESULT", "coord:  ${coord.toString()}")

        if (coord != null) {
            viewModel.setLocation(coord)
        }
    }
}

