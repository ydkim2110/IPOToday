package com.ipotoday.ipotoday.ui.base

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ipotoday.ipotoday.R

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

@BindingAdapter("isBookmarkRegistered")
fun bookmarkRegistered(v: ImageView, boolean: Boolean) {
    v.setImageResource(if (boolean) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24)
}