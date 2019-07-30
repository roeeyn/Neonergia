package dev.roeeyn.neonergia.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import dev.roeeyn.neonergia.*
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.ui.base.BaseActivity
import dev.roeeyn.neonergia.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity(), MainMvp.View {

    private val mPresenter:MainMvp.Presenter<MainMvp.View> by inject()

    private val alarmManager by lazy {
       getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private val pendingIntent by lazy {
        PendingIntent.getBroadcast(this, 0, Intent(this, TimerReceiver::class.java), 0)
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
            val newLocation = "lng: ${location?.longitude}, lat: ${location?.latitude}"
            sendDemoDeviceEntry(newLocation)
            locationManager?.removeUpdates(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mPresenter.onAttach(this)

//        val service = Intent(this, WifiReceiverService::class.java)
//        startService(service)
//
//        val serviceIntent = Intent(this, TimerService::class.java)
//        startService(serviceIntent)
//
//        fab.setOnClickListener {
//
//            //sendDemoDeviceEntry()
//            try {
//                val savedSSID = getSSIDName()?.let {
//                    sendDemoDeviceEntry("")
//                } ?: locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
//
//            } catch (ex: SecurityException) {
//                Log.e("ERROROROROR", ex.toString())
//            }
//
//        }

        fab.setOnClickListener { mPresenter.onFabClick() }

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


    }

}
