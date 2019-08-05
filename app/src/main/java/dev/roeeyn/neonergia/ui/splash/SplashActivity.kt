package dev.roeeyn.neonergia.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.roeeyn.neonergia.R
import dev.roeeyn.neonergia.ui.base.BaseActivity
import dev.roeeyn.neonergia.ui.main.MainMvp
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity(), SplashMvp.View {

    private val mPresenter: SplashMvp.Presenter<SplashMvp.View> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenter.onAttach(this)

    }

    override fun goToIntroductionScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToMainScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
