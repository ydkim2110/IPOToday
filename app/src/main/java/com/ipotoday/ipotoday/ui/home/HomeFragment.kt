package com.ipotoday.ipotoday.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.databinding.FragmentHomeBinding
import com.ipotoday.ipotoday.ui.MainFragmentDirections
import com.ipotoday.ipotoday.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                    submitList(homeViewModel.homeItemList)
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
        setHasOptionsMenu(true)
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeViewModel.clearIPOModels()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_alarm -> {
                val direction = MainFragmentDirections.actionMainFragmentToAlarmFragment()

                requireActivity().findNavController(R.id.fragment).navigate(direction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi() = with(homeViewModel) {
        selectAllIPOModelCount { count ->
            addTotal(count)
        }
        ipoList.observe(viewLifecycleOwner) { ipoList ->
            addList(ipoList)
        }
        test.observe(viewLifecycleOwner) {
            Timber.d("DEBUG : $it")
        }
    }
}