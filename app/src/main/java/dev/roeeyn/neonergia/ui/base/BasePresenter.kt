package dev.roeeyn.neonergia.ui.base

import android.os.Build
import dev.roeeyn.neonergia.data.DataManager

/**
 * Created by roeeyn in 29/03/18
 * rodrigo@mariachi.io
 * https://github.com/RN3r1
 */
abstract class BasePresenter<V: BaseMvpView>(val dataManager: DataManager)
    : BaseMvpPresenter<V> {

    lateinit var mvpView: V

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isGreaterThanAndroidM(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

}