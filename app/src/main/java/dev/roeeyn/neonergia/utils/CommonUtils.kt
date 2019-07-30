package dev.roeeyn.neonergia.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.Settings
import android.location.Geocoder
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.LocationServices
import io.reactivex.Single
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object CommonUtils {

    @SuppressLint("HardwareIds")
    fun getDeviceId(contentResolver: ContentResolver) = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)


    fun toISO8601UTC(date: Date): String {
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault())
        df.timeZone = tz
        return df.format(date)
    }

}