package dev.roeeyn.neonergia.di

import dev.roeeyn.neonergia.data.AppDataManager
import dev.roeeyn.neonergia.data.DataManager
import dev.roeeyn.neonergia.data.local.db.AppDbHelper
import dev.roeeyn.neonergia.data.local.db.DbHelper
import dev.roeeyn.neonergia.data.local.prefs.AppPreferencesHelper
import dev.roeeyn.neonergia.data.local.prefs.PreferencesHelper
import dev.roeeyn.neonergia.data.remote.ApiHelper
import dev.roeeyn.neonergia.data.remote.AppApiHelper
import org.koin.dsl.module.applicationContext

class DataModule {

    companion object {

        fun provideDataModule() = applicationContext {

            bean { AppDbHelper(get()) as DbHelper }
            bean { AppApiHelper(get()) as ApiHelper }
            bean { AppPreferencesHelper(get()) as PreferencesHelper }
            bean { AppDataManager(get(), get(), get(), get()) as DataManager }

        }

    }

}