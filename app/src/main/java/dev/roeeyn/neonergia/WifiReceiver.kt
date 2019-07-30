package dev.roeeyn.neonergia

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast
import dev.roeeyn.neonergia.utils.NetworkUtils

class WifiReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {

        val wifiStateExtra = intent?.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )

        when (wifiStateExtra) {
            WifiManager.WIFI_STATE_ENABLED -> {
                wifiReceiver?.onWifiOn()
                Log.d("YAAAAA", "WiFi is ON bitchessss: "+context?.let { NetworkUtils.getConnectedWifiName(it) })
            } // TODO we should ask if there is a shared preference if not, CREATE and ADD LOCATION
            WifiManager.WIFI_STATE_DISABLED -> {
                wifiReceiver?.onWifiOff()
                Log.d("YAAAAA", "WiFi is OFF bitchessss")
            } // TODO ask if shared preference exist, if yes DELETE sp, and device from devices list
        }

    }

    interface WifiListener {
        fun onWifiOn()
        fun onWifiOff()
    }

    companion object {
        var wifiReceiver: WifiListener? = null
    }

}