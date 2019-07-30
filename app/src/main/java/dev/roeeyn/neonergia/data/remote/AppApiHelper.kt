package dev.roeeyn.neonergia.data.remote

import android.content.Context
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.data.models.FirestoreDeviceEntry
import dev.roeeyn.neonergia.data.models.SuccessEntryPost
import dev.roeeyn.neonergia.utils.rx.SchedulerProvider
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class AppApiHelper (val context: Context): ApiHelper, KoinComponent {

    private val neonergiaApi by lazy { NeonergiaApi.create() }
    private val schedulersProvider: SchedulerProvider by inject()

    override fun postDemoEntry(demoUser: DeviceDemoResponse): Single<DeviceDemoResponse> {

        return neonergiaApi.postDemoEntry(demoUser)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .unsubscribeOn(schedulersProvider.io())

    }

    override fun deleteDeviceFromList(ssid: String, deviceId: String): Single<SuccessEntryPost> {

        return neonergiaApi.deleteDeviceFromList(ssid, deviceId)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .unsubscribeOn(schedulersProvider.io())

    }

    override fun postEntry(entry: FirestoreDeviceEntry): Single<SuccessEntryPost> {

        return neonergiaApi.postEntry(entry)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .unsubscribeOn(schedulersProvider.io())

    }

}