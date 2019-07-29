package dev.roeeyn.neonergia

import android.app.Application
import android.os.StrictMode

class NeoenergiaApp: Application(){
    override fun onCreate() {
        super.onCreate()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
//        startKoin(this, listOf(
//            DataModule.provideDataModule(),
//            ActivityModule.providePresenters(),
//            SchedulersModule.provideSchedulers()
//        ))
    }
}