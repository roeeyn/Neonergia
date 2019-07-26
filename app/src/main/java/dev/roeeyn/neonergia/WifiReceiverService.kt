package dev.roeeyn.neonergia

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.IBinder

class WifiReceiverService: Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    companion object {
        var wifiReceiver: WifiReceiver? = null
    }

    override fun onCreate() {
        registerWifiReceiver()
    }

    override fun onDestroy() {
        unregisterReceiver(wifiReceiver)
        wifiReceiver = null
    }

    private fun registerWifiReceiver(){
        wifiReceiver = WifiReceiver()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, intentFilter)
    }

}