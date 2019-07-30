package dev.roeeyn.neonergia.ui.main

import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.data.models.DeviceDemoResponse
import dev.roeeyn.neonergia.ui.base.BasePresenter
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent

class MainPresenter<V: MainMvp.View>(dataManager: DataManager):
        BasePresenter<V>(dataManager), MainMvp.Presenter<V> {

        private var localDisposable: Disposable? = null

        override fun onFabClick() {
                localDisposable = dataManager.postDemoEntry(DeviceDemoResponse(
                        "WiFi1",
                        "abc123",
                        "2019-12-12:09:09:32",
                        "lat: -82.828, lng: -122.8954"))
                        .subscribe({
                                mvpView.showMessage(it.toString())
                        }){
                                mvpView.showError(it.toString())
                        }
        }

        override fun onDestroy() = localDisposable?.dispose() ?: Unit


}