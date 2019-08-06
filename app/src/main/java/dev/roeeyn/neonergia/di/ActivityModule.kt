package dev.roeeyn.neonergia.di

import dev.roeeyn.neonergia.ui.introduction.IntroductionMvp
import dev.roeeyn.neonergia.ui.introduction.IntroductionPresenter
import dev.roeeyn.neonergia.ui.main.MainMvp
import dev.roeeyn.neonergia.ui.main.MainPresenter
import dev.roeeyn.neonergia.ui.splash.SplashMvp
import dev.roeeyn.neonergia.ui.splash.SplashPresenter
import org.koin.dsl.module.applicationContext

class ActivityModule {
    companion object {
        fun providePresenters() = applicationContext {
            bean { MainPresenter<MainMvp.View>(get()) as MainMvp.Presenter<MainMvp.View>}
            bean { SplashPresenter<SplashMvp.View>(get()) as SplashMvp.Presenter<SplashMvp.View> }
            bean { IntroductionPresenter<IntroductionMvp.View>(get()) as IntroductionMvp.Presenter<IntroductionMvp.View> }
        }
    }
}