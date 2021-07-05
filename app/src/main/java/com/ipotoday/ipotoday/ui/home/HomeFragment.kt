package com.ipotoday.ipotoday.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.navGraphViewModels
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.databinding.FragmentHomeBinding
import com.ipotoday.ipotoday.ui.InjectorUtils
import com.ipotoday.ipotoday.ui.MainFragmentDirections
import com.ipotoday.ipotoday.ui.MainViewModel
import com.ipotoday.ipotoday.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    /**
     * HomeFragment, BookmarkFragment, CalendarFragment, SettingsFragment 는 MainFragment에 종속되어있기때문에 공유 뷰모델을 사용해야함
     */

    private val mainViewModel: MainViewModel by navGraphViewModels(R.id.nav_main) {
        InjectorUtils.provideMainViewModelFactory(requireContext())
    } //hiltViewModel 로 navGraphViewModels함수로 생성하면 constuctor가 작동안됨 (힐트로 주입하는방법 찾아보기)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).run {
            recyclerView.apply {
                adapter = HomeListAdapter().apply {
                    mainViewModel.homeItemList.observe(viewLifecycleOwner) { list ->
                        submitList(list)
                    }
                    setOnItemClickListener { v, i ->
                        when (v.id) {
                            R.id.cv_ipo -> mainViewModel.getData(i)?.let { data ->
                                val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(data.id!!)

                                requireActivity().findNavController(R.id.fragment).navigate(direction)
                            }
                            R.id.iv_bookmark -> (currentList[i] as IPOModel).also { ipoModel ->
                                ipoModel.bookmark = !ipoModel.bookmark!!

                                mainViewModel.updateIPOModel(ipoModel)
                                notifyItemChanged(i)
                            }
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

    private fun subscribeUi() = with(mainViewModel) {
        selectAllIPOModelCount { count ->
            setTotal(count)
        }
        ipoList.observe(viewLifecycleOwner) { list ->
            if (ipoListValue.isEmpty()) {
                ipoListValue = list

                addAllIPOList(ipoListValue)
            }
        }
        /*homeItemList.observe(viewLifecycleOwner) { list ->
            Log.e("TEST", "$list")
        }*/
    }
}