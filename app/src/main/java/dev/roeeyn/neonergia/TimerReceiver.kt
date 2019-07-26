package dev.roeeyn.neonergia

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TimerReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            // TODO verify that it starts at boot
            val serviceIntent = Intent(context, TimerService::class.java)
            context?.startService(serviceIntent)
        } else {
            Log.d("YAAAAAA", "Alarm Manager just ran (ahuevo)")
            // TODO here we should do the post to Firebase
        }
    }

}