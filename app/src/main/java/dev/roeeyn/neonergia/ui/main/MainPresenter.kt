package dev.roeeyn.neonergia.ui.main

import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.ui.base.BasePresenter

class MainPresenter<V: MainMvp.View>(dataManager: DataManager):
        BasePresenter<V>(dataManager), MainMvp.Presenter<V> {

}