package dev.roeeyn.neonergia.ui.permissions_requests

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import dev.roeeyn.neonergia.R
import dev.roeeyn.neonergia.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_permission_request.*
import org.koin.android.ext.android.inject

class PermissionRequestActivity : BaseActivity(), PermissionRequestMvp.View {

    private val mPresenter: PermissionRequestMvp.Presenter<PermissionRequestMvp.View> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_request)
        mPresenter.onAttach(this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
