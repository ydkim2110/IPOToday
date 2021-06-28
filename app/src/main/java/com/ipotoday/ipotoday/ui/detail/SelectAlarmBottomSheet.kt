package com.ipotoday.ipotoday.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.databinding.SelectAlarmBottomSheetBinding
import com.ipotoday.ipotoday.ui.MainFragmentDirections
import com.ipotoday.ipotoday.utils.SelectAlarmType
import com.ipotoday.ipotoday.utils.autoCleared
import com.ipotoday.ipotoday.utils.extensions.setOnSingleClickListener

class SelectAlarmBottomSheet : BottomSheetDialogFragment() {

    private var binding: SelectAlarmBottomSheetBinding by autoCleared()

    interface SelectAlarmListener {
        fun onSelected(type: SelectAlarmType)
    }

    private lateinit var selectAlarmListener: SelectAlarmListener

    fun setOnSelectAlarmListener(selectAlarmListener: SelectAlarmListener) {
        this.selectAlarmListener = selectAlarmListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectAlarmBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgCloseIcon.setOnClickListener { dismiss() }
        binding.layoutCustomAlarm.setOnClickListener {
//            selectAlarmListener.onSelected(SelectAlarmType.CUSTOM_ALARM)
            findNavController().previousBackStackEntry?.savedStateHandle?.set("alarmType", SelectAlarmType.CUSTOM_ALARM.ordinal)
            findNavController().popBackStack()
            dismiss()
        }
        binding.layoutGoogleCalendar.setOnClickListener {
//            selectAlarmListener.onSelected(SelectAlarmType.GOOGLE_CALENDAR)
            findNavController().previousBackStackEntry?.savedStateHandle?.set("alarmType", SelectAlarmType.GOOGLE_CALENDAR.ordinal)
            findNavController().popBackStack()
            dismiss()
        }
    }

    companion object {
        const val TAG = "SelectAlarmBottomSheet"
    }
}