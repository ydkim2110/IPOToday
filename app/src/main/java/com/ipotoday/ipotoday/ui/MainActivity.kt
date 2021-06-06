package com.ipotoday.ipotoday.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.TestModel
import com.ipotoday.ipotoday.ui.base.BaseActivity
import com.ipotoday.ipotoday.ui.home.HomeViewModel
import com.ipotoday.ipotoday.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var sessionManager: SessionManager
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        homeViewModel.test.observe(this) {
            Timber.d("DEBUG : $it")
        }

        val testModel = TestModel(
            id = null,
            title = "IPO Today",
            testDescription = "Sdkjfdkljfdklsfjdksl"
        )
        val navController = findNavController(R.id.fragment)
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
        // 테스트 일이삼
        homeViewModel.insertTestViewModel(testModel)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}