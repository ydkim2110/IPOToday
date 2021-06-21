package com.ipotoday.ipotoday.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.databinding.FragmentDetailBinding
import com.ipotoday.ipotoday.databinding.FragmentDetailBindingImpl
import com.ipotoday.ipotoday.databinding.FragmentMainBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment
import com.ipotoday.ipotoday.utils.SelectAlarmType
import com.ipotoday.ipotoday.utils.autoCleared
import com.ipotoday.ipotoday.utils.extensions.shortToast
import timber.log.Timber

class DetailFragment : BaseFragment() {

//    override fun getFragmentBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ): FragmentDetailBinding {
//        return FragmentDetailBinding.inflate(inflater, container, false)
//    }
    private var binding: FragmentDetailBinding by autoCleared()

    private val selectAlarmBottomSheet by lazy { SelectAlarmBottomSheet() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "제주맥주"
        binding.toolbar.inflateMenu(R.menu.menu_detail)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_alarm -> {
                    showAlarmBottomSheet()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_alarm -> {
                showAlarmBottomSheet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlarmBottomSheet() {
        selectAlarmBottomSheet.isCancelable = true
        selectAlarmBottomSheet.show(childFragmentManager, SelectAlarmBottomSheet.TAG)
        selectAlarmBottomSheet.setOnSelectAlarmListener(object : SelectAlarmBottomSheet.SelectAlarmListener {
            override fun onSelected(type: SelectAlarmType) {
                when (type) {
                    SelectAlarmType.CUSTOM_ALARM -> {
                        Timber.d("DEBUG: CUSTOM_ALARM")
                    }
                    SelectAlarmType.GOOGLE_CALENDAR -> {
                        Timber.d("DEBUG: GOOGLE_CALENDAR")
                    }
                }
            }
        })
    }

    companion object {

    }

}