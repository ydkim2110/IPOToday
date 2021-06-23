package com.ipotoday.ipotoday.ui

import android.content.Context
import android.content.Intent
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.databinding.ActivityMainBinding
import com.ipotoday.ipotoday.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}