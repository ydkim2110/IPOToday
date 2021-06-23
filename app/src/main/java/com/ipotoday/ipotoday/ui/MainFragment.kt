package com.ipotoday.ipotoday.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.databinding.FragmentMainBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment
import com.ipotoday.ipotoday.utils.SessionManager
import com.ipotoday.ipotoday.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment() {
    @Inject
    lateinit var sessionManager: SessionManager

    var binding: FragmentMainBinding by autoCleared()

    var navController: NavController by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            navController = (childFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController

            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
            bottomNav.setupWithNavController(navController)
            bottomNav.setOnNavigationItemReselectedListener(fun(_: MenuItem) = Unit)
        }
        return binding.root
    }
}