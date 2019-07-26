package dev.roeeyn.neonergia

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.os.Build
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val alarmManager by lazy {
       getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private val pendingIntent by lazy {
        PendingIntent.getBroadcast(this, 0, Intent(this, TimerReceiver::class.java), 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service = Intent(this, WifiReceiverService::class.java)
        startService(service)

        val serviceIntent = Intent(this, TimerService::class.java)
        startService(serviceIntent)

    }

    fun startAlarm(){
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> alarmManager.setAndAllowWhileIdle(AlarmManager.RTC, 0, pendingIntent)
            else -> alarmManager.set(AlarmManager.RTC, 0, pendingIntent)
        }
    }

}
