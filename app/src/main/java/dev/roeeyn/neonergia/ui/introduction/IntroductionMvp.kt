package dev.roeeyn.neonergia.ui.introduction

import dev.roeeyn.neonergia.ui.base.BaseMvpPresenter
import dev.roeeyn.neonergia.ui.base.BaseMvpView

interface IntroductionMvp {

    interface View: BaseMvpView {
        fun changeCardText()
        fun goToNextScreen()
        fun isLastText(): Boolean
    }

    interface Presenter<V: IntroductionMvp.View>: BaseMvpPresenter<V> {
        fun onNextClick()
    }

}