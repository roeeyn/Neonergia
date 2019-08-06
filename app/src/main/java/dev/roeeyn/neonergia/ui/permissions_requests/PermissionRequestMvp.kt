package dev.roeeyn.neonergia.ui.permissions_requests

import dev.roeeyn.neonergia.ui.base.BaseMvpPresenter
import dev.roeeyn.neonergia.ui.base.BaseMvpView

interface PermissionRequestMvp {

    interface View: BaseMvpView {

    }

    interface Presenter<V: View>: BaseMvpPresenter<V> {

    }

}