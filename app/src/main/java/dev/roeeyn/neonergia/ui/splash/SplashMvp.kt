package dev.roeeyn.neonergia.ui.splash

import dev.roeeyn.neonergia.ui.base.BaseMvpPresenter
import dev.roeeyn.neonergia.ui.base.BaseMvpView

interface SplashMvp {

    interface View : BaseMvpView {
        fun goToMainScreen()
        fun goToIntroductionScreen()
    }

    interface Presenter<V: SplashMvp.View>: BaseMvpPresenter<V> {
        fun onCreate()
    }

}