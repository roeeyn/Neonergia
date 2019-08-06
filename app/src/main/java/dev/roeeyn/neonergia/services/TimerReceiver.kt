package dev.roeeyn.neonergia.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dev.roeeyn.neonergia.ui.permissions_requests.PermissionRequestMvp
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class TimerReceiver: BroadcastReceiver(), KoinComponent {

    private val mPresenter: PermissionRequestMvp.Presenter<PermissionRequestMvp.View> by inject()


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            // TODO verify that it starts at boot
            val serviceIntent = Intent(context, TimerService::class.java)
            context?.startService(serviceIntent)
        } else {
            Log.d("YAAAAAA", "Alarm Manager just ran (ahuevo)")
            mPresenter.sendDataToFirestore()
        }
    }

}