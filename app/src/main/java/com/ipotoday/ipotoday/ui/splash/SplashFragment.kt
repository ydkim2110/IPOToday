package com.ipotoday.ipotoday.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ipotoday.ipotoday.databinding.FragmentSplashBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {
    private val splashViewModel by viewModels<SplashViewModel>()

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

        splashViewModel.launchLiveData.observe(viewLifecycleOwner) { isLaunch ->
            if (isLaunch) {
                findNavController().navigate(direction)
            }
        }
    }
}