package dev.roeeyn.neonergia.data.remote

import android.content.Context
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
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

}