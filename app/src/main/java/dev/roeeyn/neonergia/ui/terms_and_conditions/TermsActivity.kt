package dev.roeeyn.neonergia.ui.terms_and_conditions

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import dev.roeeyn.neonergia.R

import kotlinx.android.synthetic.main.activity_terms.*
import android.text.method.ScrollingMovementMethod
import dev.roeeyn.neonergia.utils.toast
import kotlinx.android.synthetic.main.content_terms.*


class TermsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        terms_text.movementMethod = ScrollingMovementMethod()


        fab.setOnClickListener { view ->
            toast("Hey ${terms_checkbox.isChecked}")
        }



    }

}
