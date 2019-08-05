package dev.roeeyn.neonergia.ui.main

import dev.roeeyn.neonergia.ui.base.BaseMvpPresenter
import dev.roeeyn.neonergia.ui.base.BaseMvpView

interface MainMvp {
    interface View : BaseMvpView {

    }

    interface Presenter<V:MainMvp.View>: BaseMvpPresenter<V> {
        fun onFabClick()
        fun sendDataToFirestore()
    }
}