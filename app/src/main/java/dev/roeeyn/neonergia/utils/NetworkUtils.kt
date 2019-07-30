package dev.roeeyn.neonergia.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager

object NetworkUtils {

    fun getConnectedWifiName(context: Context): String? {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        return wifiManager?.connectionInfo?.ssid
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiCheck = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return wifiCheck?.isConnectedOrConnecting ?: false
    }

}