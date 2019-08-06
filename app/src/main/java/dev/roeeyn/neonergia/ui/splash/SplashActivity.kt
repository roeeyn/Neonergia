package dev.roeeyn.neonergia.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.roeeyn.neonergia.R
import dev.roeeyn.neonergia.ui.base.BaseActivity
import dev.roeeyn.neonergia.ui.introduction.IntroductionActivity
import dev.roeeyn.neonergia.ui.permissions_requests.PermissionRequestActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity(), SplashMvp.View {

    private val mPresenter: SplashMvp.Presenter<SplashMvp.View> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenter.onAttach(this)
        mPresenter.onCreate()
    }

    override fun goToIntroductionScreen() {
        startActivity(Intent(this, IntroductionActivity::class.java))
        finish()
    }

    override fun goToMainScreen() {
        startActivity(Intent(this, PermissionRequestActivity::class.java))
        finish()
    }

}
