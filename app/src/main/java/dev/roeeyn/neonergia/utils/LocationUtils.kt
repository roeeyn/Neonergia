package dev.roeeyn.neonergia.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

object LocationUtils {

    fun getLastLocation(context: Context): Single<String> {
        return Single.create { e ->
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {

                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

                fusedLocationClient.lastLocation.addOnSuccessListener { location ->

                    location?.let { e.onSuccess("lat: ${it.latitude}, lng: ${it.longitude}") }
                        ?: e.onError(Throwable("Your location settings is turned off"))

                }

            } else e.onError(Throwable("You haven't given the permissions"))
        }
    }

}