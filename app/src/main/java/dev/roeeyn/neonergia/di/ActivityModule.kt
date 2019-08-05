package dev.roeeyn.neonergia.di

import dev.roeeyn.neonergia.ui.main.MainMvp
import dev.roeeyn.neonergia.ui.main.MainPresenter
import dev.roeeyn.neonergia.ui.splash.SplashMvp
import dev.roeeyn.neonergia.ui.splash.SplashPresenter
import org.koin.dsl.module.applicationContext

class ActivityModule {
    companion object {
        fun providePresenters() = applicationContext {
            bean { MainPresenter<MainMvp.View>(get()) }
            bean { SplashPresenter<SplashMvp.View>(get()) as SplashMvp.Presenter<SplashMvp.View> }
        }
    }
}