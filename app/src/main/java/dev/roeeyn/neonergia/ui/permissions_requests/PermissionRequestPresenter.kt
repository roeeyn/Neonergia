package dev.roeeyn.neonergia.ui.permissions_requests

import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.ui.base.BasePresenter
import org.koin.standalone.KoinComponent

class PermissionRequestPresenter<V: PermissionRequestMvp.View>(dataManager: DataManager):
    BasePresenter<V>(dataManager), PermissionRequestMvp.Presenter<V>, KoinComponent{
}