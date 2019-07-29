package dev.roeeyn.neonergia.di

import dev.roeeyn.neonergia.utils.rx.AppSchedulerProvider
import dev.roeeyn.neonergia.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module.applicationContext

class SchedulersModule {

    companion object {

        fun provideSchedulers() = applicationContext {

            factory { AppSchedulerProvider() as SchedulerProvider }
            factory { CompositeDisposable() }

        }

    }

}