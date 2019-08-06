package dev.roeeyn.neonergia.ui.permissions_requests

import android.content.Context
import android.util.Log
import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.data.models.FirestoreDeviceEntry
import dev.roeeyn.neonergia.ui.base.BasePresenter
import dev.roeeyn.neonergia.utils.CommonUtils
import dev.roeeyn.neonergia.utils.LocationUtils
import dev.roeeyn.neonergia.utils.NetworkUtils
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PermissionRequestPresenter<V: PermissionRequestMvp.View>(dataManager: DataManager):
    BasePresenter<V>(dataManager), PermissionRequestMvp.Presenter<V>, KoinComponent{

    private var httpDisposable: Disposable? = null
    private var locationDisposable: Disposable? = null

    private val context: Context by inject()

    override fun tryToRegisterBroadcast() {
        if(!dataManager.isBroadcastRegistered()){
            dataManager.saveBroadcasterFlag()
            mvpView.registerBroadcast()
        }
    }


    override fun sendDataToFirestore() {
        intentCreateEntry()
    }

    private fun intentCreateEntry(){

        if(NetworkUtils.isNetworkConnected(context)){

            val ssid = NetworkUtils.getConnectedWifiName(context)

            ssid?.let { existingSSID ->
                locationDisposable = LocationUtils.getLastLocation(context).subscribe({ location ->
                    httpDisposable = dataManager.postEntry(createEntry(existingSSID, location))
                        .subscribe({
                            //TODO debería ser mandado en una notificación, pues el toast es complicado
                            // mvpView.showMessage("Added entry with location")
                            Log.d("EAEAEAEAEA", "Added entry with location")
                            dataManager.saveActualWifiName(existingSSID)
                        }) {
                            // mvpView.showError(it.toString())
                            Log.d("EAEAEAEAEA", it.toString())
                        }
                }){
                    // mvpView.showError("Valio vrg ${it.message}")
                    Log.d("RIKOPOLLO", "Valio vrg ${it.message}")
                }

            } ?: Log.d("RIKOPOLLO", "No Wifi Connected yet") // mvpView.showError("No Wifi Connected yet")

        } else {
            val deviceId = CommonUtils.getDeviceId(context.contentResolver)

            val savedSSID = dataManager.getActualWifiName()

            savedSSID?.let {

                httpDisposable = dataManager.deleteDeviceFromList(it, deviceId)
                    .subscribe({
                        // mvpView.showMessage("Dispositivo borrado")
                        Log.d("RIKOPOLLO", "Dispositivo borrado")
                        dataManager.deleteWifiName()
                    }){
                        // mvpView.showMessage("Error al borrar dispositivo")
                        Log.d("RIKOPOLLO", "Error al borrar dispositivo")
                    }

            } ?: Log.d("RIKOPOLLO", "No hay wifi guardado, no se hace nada") //mvpView.showMessage("No hay wifi guardado, no se hace nada")


        }



    }

    private fun createEntry(ssid:String, location: String): FirestoreDeviceEntry {
        val timestamp = ""
        val deviceId = CommonUtils.getDeviceId(context.contentResolver)
        return FirestoreDeviceEntry(ssid, deviceId, timestamp, location)
    }

    override fun onDestroy(){
        httpDisposable?.dispose()
    }

}