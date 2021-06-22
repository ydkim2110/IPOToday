package com.ipotoday.ipotoday.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.databinding.FragmentAlarmBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment

class AlarmFragment : BaseFragment() {
    private lateinit var  binding:FragmentAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "알림목록"
    }

    companion object {

    }
}