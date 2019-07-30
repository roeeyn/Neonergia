package dev.roeeyn.neonergia.ui.base

/**
 * Created by roeeyn in 29/03/18
 * rodrigo@mariachi.io
 * https://github.com/RN3r1
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun onAttach(mvpView: V)
    fun onDestroy()
    fun isGreaterThanAndroidM():Boolean

}