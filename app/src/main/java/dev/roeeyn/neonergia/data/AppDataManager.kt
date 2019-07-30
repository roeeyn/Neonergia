package dev.roeeyn.neonergia.data

import android.content.Context
import dev.roeeyn.neonergia.data.local.db.DbHelper
import dev.roeeyn.neonergia.data.local.prefs.PreferencesHelper
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.data.models.FirestoreDeviceEntry
import dev.roeeyn.neonergia.data.models.SuccessEntryPost
import dev.roeeyn.neonergia.data.remote.ApiHelper
import io.reactivex.Single

class AppDataManager(
    val context: Context,
    private val dbHelper: DbHelper,
    private val preferencesHelper: PreferencesHelper,
    private val apiHelper: ApiHelper
):DataManager {

    override fun postEntry(entry: FirestoreDeviceEntry): Single<SuccessEntryPost> = apiHelper.postEntry(entry)

    override fun saveActualWifiName(ssid: String) = preferencesHelper.saveActualWifiName(ssid)

    override fun getActualWifiName(): String? = preferencesHelper.getActualWifiName()

    override fun postDemoEntry(demoUser: DeviceDemoResponse): Single<DeviceDemoResponse> = apiHelper.postDemoEntry(demoUser)

    override fun deleteWifiName() = preferencesHelper.deleteWifiName()

    override fun deleteDeviceFromList(ssid: String, deviceId: String): Single<SuccessEntryPost> =
        apiHelper.deleteDeviceFromList(ssid, deviceId)

}