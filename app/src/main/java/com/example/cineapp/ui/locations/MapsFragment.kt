package com.example.cineapp.ui.locations

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.cineapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date


class MapsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var db = FirebaseFirestore.getInstance()

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        requestPermissionOfLocation()
    }


    private fun requestPermissionOfLocation() {
        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }


    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                // Create a new user with a first and last name
                                val user: MutableMap<String, Any> = HashMap()
                                user["latitude"] = location.latitude
                                user["longitude"] = location.longitude
                                user["time_register"] = Date().time

                                // Add a new document with a generated ID
                                db.collection("locations")
                                    .add(user)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(
                                            "LOCATION",
                                            "guardando ubicacion en firebase => " + location.latitude.toString() + " " + location.longitude.toString()
                                        )
                                        requestPermissionOfLocation()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            "LOCATION",
                                            "Error al guardar ubicacion",
                                            e
                                        )
                                        requestPermissionOfLocation()
                                    }

                            }, 300000)
                        }
                    }
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                // Precise location access granted.
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                // Create a new user with a first and last name
                                val user: MutableMap<String, Any> = HashMap()
                                user["latitude"] = location.latitude
                                user["longitude"] = location.longitude
                                user["time_register"] = Date().time

                                // Add a new document with a generated ID
                                db.collection("locations")
                                    .add(user)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(
                                            "LOCATION",
                                            "guardando ubicacion en firebase => " + location.latitude.toString() + " " + location.longitude.toString()
                                        )
                                        requestPermissionOfLocation()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            "LOCATION",
                                            "Error al guardar ubicacion",
                                            e
                                        )
                                        requestPermissionOfLocation()
                                    }
                            }, 300000)
                        }
                    }
            }
            else -> {
                // No location access granted.
                Snackbar.make(
                    requireView(),
                    "No tienes permisos de ubicacion",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}