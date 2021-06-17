package com.ipotoday.ipotoday.ui.base

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(v: ImageView, path: String) {
    Glide.with(v.context)
        .load(path)
        .into(v)
}

@BindingAdapter("image")
fun loadImage(v: ImageView, path: Uri) {
    Glide.with(v.context)
        .load(path)
        .into(v)
}