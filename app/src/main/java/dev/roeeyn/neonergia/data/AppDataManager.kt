package dev.roeeyn.neonergia.data

import android.content.Context
import dev.roeeyn.neonergia.data.local.db.DbHelper
import dev.roeeyn.neonergia.data.local.prefs.PreferencesHelper
import dev.roeeyn.neonergia.data.remote.ApiHelper

class AppDataManager(
    val context: Context,
    val dbHelper: DbHelper,
    val preferencesHelper: PreferencesHelper,
    val apiHelper: ApiHelper
):DataManager {

}