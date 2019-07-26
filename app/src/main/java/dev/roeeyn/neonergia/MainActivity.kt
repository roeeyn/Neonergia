package dev.roeeyn.neonergia

import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent



class MainActivity : AppCompatActivity(), WifiReceiver.WifiListener {

    private val wifiManager by lazy {
        applicationContext.getSystemService(Context.WIFI_SERVICE)
    }

    // private val wifiReceiver = WifiReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // WifiReceiver.wifiReceiver = this
        val service = Intent(this, WifiReceiverService::class.java)
        startService(service)
        // val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        // registerReceiver(wifiReceiver, intentFilter)

    }

    override fun onStop() {
        super.onStop()
        // unregisterReceiver(wifiReceiver)
    }

    override fun onWifiOn() {
        toast("WiFi is ON")
        toast("SSID: "+getSSID())
    }

    override fun onWifiOff() {
        toast("WiFi is OFF")
    }

    fun getSSID(): String {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val info = wifiManager!!.connectionInfo
        return info.ssid
    }


}
