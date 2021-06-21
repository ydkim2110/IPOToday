package com.ipotoday.ipotoday.utils.extensions

import android.view.View
import com.ipotoday.ipotoday.utils.OnSingleClickListener

fun View.setOnSingleClickListener(action: (View) -> Unit) {
    val onClick = OnSingleClickListener {
        action(it)
    }
    setOnClickListener(onClick)
}