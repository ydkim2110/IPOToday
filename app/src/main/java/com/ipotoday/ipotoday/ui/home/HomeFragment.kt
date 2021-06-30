package com.ipotoday.ipotoday.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.databinding.FragmentHomeBinding
import com.ipotoday.ipotoday.ui.MainFragmentDirections
import com.ipotoday.ipotoday.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
                    homeViewModel.homeItemList.observe(viewLifecycleOwner) { list ->
                        submitList(list)
                    }
                    setOnItemClickListener { _, i ->
                        homeViewModel.getData(i)?.let { data ->
                            /*val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(data.id!!)

                            requireActivity().findNavController(R.id.fragment).navigate(direction)*/
                            homeViewModel.addItem("테[스트")
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
        requireActivity().findNavController(R.id.fragment).addOnDestinationChangedListener { _, navDestination, _ ->
            if (navDestination.id == R.id.alarmFragment) {
                Log.e("TEST", "테스트트트트트")
            }
        }
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
            //if (requireActivity().findNavController(R.id.fragment).currentDestination?.id == R.id.alarmFragment)
            addList(ipoList)
        }
        homeItemList.observe(viewLifecycleOwner) { list ->
            Log.e("TEST", "$list")
        }
    }
}