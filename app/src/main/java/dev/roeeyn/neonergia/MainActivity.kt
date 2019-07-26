package dev.roeeyn.neonergia

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val alarmManager by lazy {
       getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private val pendingIntent by lazy {
        PendingIntent.getBroadcast(this, 0, Intent(this, TimerReceiver::class.java), 0)
    }

    private val deviceApiService by lazy {
        DeviceServiceFactory.createService()
    }

    private val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    }

    private val sharedPreferences by lazy {
        getSharedPreferences("neoenergia_sp", Context.MODE_PRIVATE)
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLocationChanged(location: Location?) {
            toast("lng: ${location?.longitude}, lat: ${location?.latitude} ")
            val location = "lng: ${location?.longitude}, lat: ${location?.latitude}"
            sendDemoDeviceEntry(location)
            locationManager?.removeUpdates(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service = Intent(this, WifiReceiverService::class.java)
        startService(service)

        val serviceIntent = Intent(this, TimerService::class.java)
        startService(serviceIntent)

        fab.setOnClickListener {

            //sendDemoDeviceEntry()
            try {
                val savedSSID = getSSIDName()?.let {
                    sendDemoDeviceEntry("")
                } ?: locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)

            } catch (ex: SecurityException) {
                Log.e("ERROROROROR", ex.toString())
            }

        }

    }

    fun saveSSIDName(ssid: String){
        val editor = sharedPreferences.edit()
        editor.putString("SSID_NAME", ssid)
        editor.apply()
    }

    fun getSSIDName():String?{
        val ssid = sharedPreferences.getString("SSID_NAME", null)
        Log.d("IHATEYOU", ssid?.toString() ?: "NO WIFI MADAFAKA")
        return ssid
    }

    fun sendDemoDeviceEntry(location : String){

        val ssid = WifiReceiver.getSSID(this)
        saveSSIDName(ssid)
        val timeStamp: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        deviceApiService
            .postDemoEntry(DeviceDemoResponse(ssid, deviceId, timeStamp, location))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { toast("Iniciando petición") }
            .doFinally { toast("Finalizó petición") }
            .subscribeBy(
                onSuccess = {
                    Log.d("HackademyTag", it.toString())
                },
                onError = {
                    Log.e("HackademyTag", it.toString())
                }
            )
    }

}
