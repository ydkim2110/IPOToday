package com.ipotoday.ipotoday.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ipotoday.ipotoday.databinding.FragmentSplashBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSplashBinding.inflate(inflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val direction = SplashFragmentDirections.actionSplashFragmentToMainFragment()

        lifecycleScope.launch {
            delay(SPLASH_TIME)
            findNavController().navigate(direction)
        }
    }

    companion object {
        private const val SPLASH_TIME = 2000L
    }
}