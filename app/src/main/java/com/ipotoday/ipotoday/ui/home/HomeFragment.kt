package com.ipotoday.ipotoday.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.TestModel
import com.ipotoday.ipotoday.databinding.FragmentHomeBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.test.observe(this) {
            Timber.d("DEBUG : $it")
        }

        val testModel = TestModel(
            id = null,
            title = "IPO Today",
            testDescription = "Sdkjfdkljfdklsfjdksl"
        )
        // 테스트 일이삼
        homeViewModel.insertTestViewModel(testModel)
    }
}