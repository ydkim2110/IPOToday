package com.ipotoday.ipotoday.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.shortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.longToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()