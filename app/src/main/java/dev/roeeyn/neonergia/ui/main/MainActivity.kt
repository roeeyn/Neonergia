package dev.roeeyn.neonergia.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import dev.roeeyn.neonergia.*
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.ui.base.BaseActivity
import dev.roeeyn.neonergia.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity(), MainMvp.View {

    private val mPresenter:MainMvp.Presenter<MainMvp.View> by inject()

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
        mPresenter.onAttach(this)

//        val service = Intent(this, WifiReceiverService::class.java)
//        startService(service)
//
//        val serviceIntent = Intent(this, TimerService::class.java)
//        startService(serviceIntent)

        fab.setOnClickListener { mPresenter.onFabClick() }

    }

}
