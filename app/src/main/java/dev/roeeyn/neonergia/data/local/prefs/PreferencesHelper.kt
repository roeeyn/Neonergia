package dev.roeeyn.neonergia.data.local.prefs

interface PreferencesHelper {

    fun saveActualWifiName(ssid: String)
    fun getActualWifiName():String?
    fun deleteWifiName()

}