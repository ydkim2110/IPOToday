package com.ipotoday.ipotoday.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView

abstract class BaseActivity<ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {
    protected lateinit var binding: androidx.databinding.ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, layoutId)
    }
}