package dev.roeeyn.neonergia.ui.terms_and_conditions

import dev.roeeyn.neonergia.ui.base.BaseMvpPresenter
import dev.roeeyn.neonergia.ui.base.BaseMvpView

interface TermsMvp {

    interface View: BaseMvpView {
        fun areTermsApproved(): Boolean
        fun goToPermissionActivity()
    }

    interface Presenter<V: TermsMvp.View>: BaseMvpPresenter<V> {
        fun onNextClicked()
    }

}