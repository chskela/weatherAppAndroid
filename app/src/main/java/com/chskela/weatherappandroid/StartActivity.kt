package com.chskela.weatherappandroid

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.chskela.weatherappandroid.databinding.ActivityStartBinding
import com.chskela.weatherappandroid.network.data.Coord
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    // The Fused Location Provider provides access to location APIs.
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    // Allows class to cancel the location request if it exits the activity.
    // Typically, you use one cancellation source per lifecycle.
    private var cancellationTokenSource = CancellationTokenSource()

    // If the user denied a previous permission request, but didn't check "Don't ask again", this
    // Snackbar provides an explanation for why user should approve, i.e., the additional rationale.
    private val coarseLocationRationalSnackbar by lazy {
        Snackbar.make(
            binding.container,
            R.string.coarse_location_permission_rationale,
            Snackbar.LENGTH_LONG
        ).setAction(R.string.ok) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_COARSE_LOCATION_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.container)
    }

    override fun onStop() {
        super.onStop()
        // Cancels location request (if in flight).
        cancellationTokenSource.cancel()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionResult()")

        if (requestCode == REQUEST_COARSE_LOCATION_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() ->
                    // If user interaction was interrupted, the permission request
                    // is cancelled and you receive an empty array.
                    Log.d(TAG, "User interaction was cancelled.")

                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    Snackbar.make(
                        binding.container,
                        R.string.permission_approved_explanation,
                        Snackbar.LENGTH_LONG
                    )
                        .show()

                else -> {
                    Snackbar.make(
                        binding.container,
                        R.string.coarse_permission_denied_explanation,
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(R.string.settings) {
                            // Build intent that displays the App settings screen.
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                BuildConfig.APPLICATION_ID,
                                null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        .show()
                }
            }
        }
    }

    fun locationRequestOnClick(view: View) {
        Log.d(TAG, "locationRequestOnClick()")

        val permissionApproved =
            applicationContext.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permissionApproved) {
            requestCurrentLocation()
        } else {
            requestPermissionWithRationale(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                REQUEST_COARSE_LOCATION_PERMISSIONS_REQUEST_CODE,
                coarseLocationRationalSnackbar
            )
        }
    }

    /**
     * Gets current location.
     * Note: The code checks for permission before calling this method, that is, it's never called
     * from a method with a missing permission. Also, I include a second check with my extension
     * function in case devs just copy/paste this code.
     */
    @SuppressLint("MissingPermission")
    private fun requestCurrentLocation() {
        Log.d(TAG, "requestCurrentLocation()")
        if (applicationContext.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {

            // Returns a single current location fix on the device. Unlike getLastLocation() that
            // returns a cached location, this method could cause active location computation on the
            // device. A single fresh location will be returned if the device location can be
            // determined within reasonable time (tens of seconds), otherwise null will be returned.
            //
            // Both arguments are required.
            // PRIORITY type is self-explanatory. (Other options are PRIORITY_BALANCED_POWER_ACCURACY,
            // PRIORITY_LOW_POWER, and PRIORITY_NO_POWER.)
            // The second parameter, [CancellationToken] allows the activity to cancel the request
            // before completion.
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful && task.result != null) {
                    val result: Location = task.result
                    Log.d(TAG, "Location (success): ${result.latitude}, ${result.longitude}")
                    val coord = Coord(result.latitude, result.longitude)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(LOCATION, coord)
                    startActivity(intent)
                } else {
                    val exception = task.exception
                    Log.d(TAG, "Location (failure): $exception")
                }
            }
        }
    }

    companion object {
        private const val TAG = "StartActivity"
        const val LOCATION = "location"
        private const val REQUEST_COARSE_LOCATION_PERMISSIONS_REQUEST_CODE = 34
    }
}

