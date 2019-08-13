package dev.roeeyn.neonergia.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import dev.roeeyn.neonergia.utils.AppConstants.BROADCAST_RECEIVER
import dev.roeeyn.neonergia.utils.AppConstants.PREF_NAME
import dev.roeeyn.neonergia.utils.AppConstants.WIFI_SP_KEY

class AppPreferencesHelper(val context: Context): PreferencesHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun saveActualWifiName(ssid: String) = mPrefs.edit().putString(WIFI_SP_KEY, ssid).apply()

    override fun getActualWifiName(): String? = mPrefs.getString(WIFI_SP_KEY, null)

    override fun deleteWifiName() = mPrefs.edit().remove(WIFI_SP_KEY).apply()

    override fun saveBroadcasterFlag() = mPrefs.edit().putBoolean(BROADCAST_RECEIVER, true).apply()

    override fun isBroadcastRegistered(): Boolean = mPrefs.getBoolean(BROADCAST_RECEIVER, false)

}