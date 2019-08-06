package dev.roeeyn.neonergia.ui.introduction

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import dev.roeeyn.neonergia.R

import kotlinx.android.synthetic.main.activity_introduction.*

class IntroductionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        // TODO teh text need to be changed from 20sp to 18 to fitting well on screen

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
