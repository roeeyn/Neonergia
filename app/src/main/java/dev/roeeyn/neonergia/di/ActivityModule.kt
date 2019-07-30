package dev.roeeyn.neonergia.di

import dev.roeeyn.neonergia.ui.main.MainMvp
import dev.roeeyn.neonergia.ui.main.MainPresenter
import org.koin.dsl.module.applicationContext

class ActivityModule {
    companion object {
        fun providePresenters() = applicationContext {
            bean { MainPresenter<MainMvp.View>(get()) as MainMvp.Presenter<MainMvp.View> }
        }
    }
}