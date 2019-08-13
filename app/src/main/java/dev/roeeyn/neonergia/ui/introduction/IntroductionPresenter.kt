package dev.roeeyn.neonergia.ui.introduction

import android.content.Context
import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.ui.base.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class IntroductionPresenter<V: IntroductionMvp.View>(dataManager: DataManager):
    BasePresenter<V>(dataManager), IntroductionMvp.Presenter<V>, KoinComponent {

    private val context: Context by inject()

    override fun onNextClick() = with(mvpView) {
        if (isLastText()) goToNextScreen() else changeCardText()
    }


}