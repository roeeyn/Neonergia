package dev.roeeyn.neonergia.ui.permissions_requests

import android.Manifest
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import dev.roeeyn.neonergia.R
import dev.roeeyn.neonergia.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_permission_request.*
import org.koin.android.ext.android.inject
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import dev.roeeyn.neonergia.services.TimerService
import dev.roeeyn.neonergia.utils.toast
import kotlinx.android.synthetic.main.content_permission_request.*


class PermissionRequestActivity : BaseActivity(), PermissionRequestMvp.View {

    private val mPresenter: PermissionRequestMvp.Presenter<PermissionRequestMvp.View> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_request)
        mPresenter.onAttach(this)

        permission_switch.isChecked = ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        permission_switch.setOnClickListener {
            checkLocationPermission()
        }

    }

    override fun registerBroadcast() {
        Log.d("SERVICIO", "Starting service")
        val serviceIntent = Intent(this, TimerService::class.java)
        startService(serviceIntent)
    }

    private fun checkLocationPermission(){

        Dexter.withActivity(this)
            .withPermission(permission.ACCESS_COARSE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    toast("Obrigada! Suas autorizações serão muito uteis para a melhoria do nosso serviço.")
                    permission_switch.isChecked = true
                    mPresenter.tryToRegisterBroadcast()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    toast("Nosso serviço depende do nosso acesso ao seu GPS. Todos os dados coletados são anônimos.")
                    permission_switch.isChecked = false
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

}
