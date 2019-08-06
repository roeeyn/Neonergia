package dev.roeeyn.neonergia.ui.terms_and_conditions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import dev.roeeyn.neonergia.R

import kotlinx.android.synthetic.main.activity_terms.*
import android.text.method.ScrollingMovementMethod
import dev.roeeyn.neonergia.ui.base.BaseActivity
import dev.roeeyn.neonergia.ui.permissions_requests.PermissionRequestActivity
import dev.roeeyn.neonergia.utils.toast
import kotlinx.android.synthetic.main.content_terms.*
import org.koin.android.ext.android.inject


class TermsActivity : BaseActivity(), TermsMvp.View {

    private val mPresenter: TermsMvp.Presenter<TermsMvp.View> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        mPresenter.onAttach(this)
        terms_text.movementMethod = ScrollingMovementMethod()

        fab.setOnClickListener { mPresenter.onNextClicked() }

    }

    override fun areTermsApproved(): Boolean = terms_checkbox.isChecked

    override fun goToPermissionActivity() {
        startActivity(Intent(this, PermissionRequestActivity::class.java))
        finish()
    }


}
