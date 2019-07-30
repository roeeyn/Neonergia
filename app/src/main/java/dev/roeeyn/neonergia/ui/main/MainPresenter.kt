package dev.roeeyn.neonergia.ui.main

import android.content.Context
import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.data.models.FirestoreDeviceEntry
import dev.roeeyn.neonergia.ui.base.BasePresenter
import dev.roeeyn.neonergia.utils.CommonUtils
import dev.roeeyn.neonergia.utils.LocationUtils
import dev.roeeyn.neonergia.utils.NetworkUtils
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class MainPresenter<V: MainMvp.View>(dataManager: DataManager):
    BasePresenter<V>(dataManager), MainMvp.Presenter<V>, KoinComponent {

    private var httpDisposable: Disposable? = null
    private var locationDisposable: Disposable? = null

    private val context: Context by inject()

    override fun onFabClick() {

        intentCreateEntry()

    }

    private fun intentCreateEntry(){

        if(NetworkUtils.isNetworkConnected(context)){

            val ssid = NetworkUtils.getConnectedWifiName(context)

            ssid?.let { existingSSID ->
                locationDisposable = LocationUtils.getLastLocation(context).subscribe({ location ->
                    httpDisposable = dataManager.postEntry(createEntry(existingSSID, location))
                        .subscribe({
                            mvpView.showMessage("Added entry with location")
                            dataManager.saveActualWifiName(existingSSID)
                        }) {
                            mvpView.showError(it.toString())
                        }
                }){
                    mvpView.showError("Valio vrg ${it.message}")
                }

            } ?: mvpView.showError("No Wifi Connected yet")

        } else {
            // TODO if is NO connected and was connected before
            val deviceId = CommonUtils.getDeviceId(context.contentResolver)

            val savedSSID = dataManager.getActualWifiName()

            savedSSID?.let {

                httpDisposable = dataManager.deleteDeviceFromList(it, deviceId)
                    .subscribe({
                        mvpView.showMessage("Dispositivo borrado")
                        dataManager.deleteWifiName()
                    }){
                        mvpView.showMessage("Error al borrar dispositivo")
                    }

            } ?: mvpView.showMessage("No hay wifi guardado, no se hace nada")


        }



    }

    private fun createEntry(ssid:String, location: String): FirestoreDeviceEntry{
        val timestamp = CommonUtils.toISO8601UTC(Date())
        val deviceId = CommonUtils.getDeviceId(context.contentResolver)
        return FirestoreDeviceEntry(ssid, deviceId, timestamp, location)
    }

    override fun onDestroy(){
        httpDisposable?.dispose()
    }


}