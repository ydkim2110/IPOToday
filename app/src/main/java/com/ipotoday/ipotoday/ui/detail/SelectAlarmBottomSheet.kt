package com.ipotoday.ipotoday.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ipotoday.ipotoday.databinding.SelectAlarmBottomSheetBinding
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

        binding.imgCloseIcon.setOnSingleClickListener { dismiss() }
        binding.layoutCustomAlarm.setOnSingleClickListener {
            selectAlarmListener.onSelected(SelectAlarmType.CUSTOM_ALARM)
            dismiss()
        }
        binding.layoutGoogleCalendar.setOnSingleClickListener {
            selectAlarmListener.onSelected(SelectAlarmType.GOOGLE_CALENDAR)
            dismiss()
        }
    }

    companion object {
        const val TAG = "SelectAlarmBottomSheet"
    }
}