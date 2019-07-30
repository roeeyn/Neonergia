package dev.roeeyn.neonergia

import android.app.Application
import android.os.StrictMode
import dev.roeeyn.neonergia.di.ActivityModule
import dev.roeeyn.neonergia.di.DataModule
import dev.roeeyn.neonergia.di.SchedulersModule
import org.koin.android.ext.android.startKoin

class NeoenergiaApp: Application(){
    override fun onCreate() {
        super.onCreate()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        startKoin(this, listOf(
            DataModule.provideDataModule(),
            ActivityModule.providePresenters(),
            SchedulersModule.provideSchedulers()
        ))
    }
}