package com.ipotoday.ipotoday.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil.setContentView
import com.ipotoday.ipotoday.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}