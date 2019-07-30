package dev.roeeyn.neonergia.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, message, duration).show()

fun AppCompatActivity.toast(message: String, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, message, duration).show()