package dev.roeeyn.neonergia.ui.base

/**
 * Created by roeeyn in 29/03/18
 * rodrigo@mariachi.io
 * https://github.com/RN3r1
 */
interface BaseMvpView {

    fun hideKeyboard()
    fun showError(error:String)
    fun showError(errorId:Int)
    fun showMessage(message:String)
    fun showMessage(messageId:Int)

}