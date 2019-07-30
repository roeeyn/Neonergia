package dev.roeeyn.neonergia.data

import dev.roeeyn.neonergia.data.local.db.DbHelper
import dev.roeeyn.neonergia.data.local.prefs.PreferencesHelper
import dev.roeeyn.neonergia.data.remote.ApiHelper

interface DataManager: ApiHelper, PreferencesHelper, DbHelper {
}