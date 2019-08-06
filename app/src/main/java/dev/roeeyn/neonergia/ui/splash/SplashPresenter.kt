package dev.roeeyn.neonergia.ui.splash

import android.content.Context
import android.os.Handler
import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.ui.base.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SplashPresenter<V: SplashMvp.View>(dataManager: DataManager):
    BasePresenter<V>(dataManager), SplashMvp.Presenter<V>, KoinComponent {

    private val context: Context by inject()

    override fun onCreate() {
        val handler = Handler()
        handler.postDelayed({
            if(dataManager.isBroadcastRegistered()) mvpView.goToMainScreen() else mvpView.goToIntroductionScreen()
        }, 1500)
    }

}