package dev.roeeyn.neonergia.ui.terms_and_conditions

import android.content.Context
import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.ui.base.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class TermsPresenter<V: TermsMvp.View>(dataManager: DataManager):
    BasePresenter<V>(dataManager), TermsMvp.Presenter<V>, KoinComponent {

    private val context: Context by inject()


}