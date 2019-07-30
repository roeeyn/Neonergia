package dev.roeeyn.neonergia.ui.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import dev.roeeyn.neonergia.utils.toast

/**
 * Created by roeeyn in 29/03/18
 * rodrigo@mariachi.io
 * https://github.com/RN3r1
 */
abstract class BaseActivity: AppCompatActivity(), BaseMvpView {

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun showError(error: String) = toast(error)

    override fun showError(errorId: Int) = toast(getString(errorId))

    override fun showMessage(message: String) = toast(message)

    override fun showMessage(messageId: Int) = toast(getString(messageId))


}