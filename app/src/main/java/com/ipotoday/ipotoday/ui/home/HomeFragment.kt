package com.ipotoday.ipotoday.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.TestModel
import com.ipotoday.ipotoday.databinding.FragmentHomeBinding
import com.ipotoday.ipotoday.ui.MainFragment
import com.ipotoday.ipotoday.ui.MainFragmentDirections
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
        return FragmentHomeBinding.inflate(inflater, container, false).run {
            recyclerView.apply {
                adapter = HomeListAdapter().apply {
                    homeViewModel.tempList.observe(viewLifecycleOwner) { list ->
                        submitList(list)
                    }
                    setOnItemClickListener { _, i ->
                        homeViewModel.getData(i)?.let { data ->
                            val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(data.id!!)

                            requireActivity().findNavController(R.id.fragment).navigate(direction)
                        }
                    }
                }
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(parentFragment?.parentFragment as? MainFragment)?.setUpMenuItem()
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


    /*private fun setUpMenuItem() {
        Log.e("TEST", "${(parentFragment?.parentFragment as? MainFragment)?.binding?.toolbar?.title}")
        (parentFragment?.parentFragment as? MainFragment)?.binding
            ?.toolbar
            ?.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_alarm -> {
                        findNavController().navigate(MainFragmentDirections.actionMainFragmentToAlarmFragment())
                        true
                    }
                    else -> false
                }
            }
        setHasOptionsMenu(true)
    }*/
}