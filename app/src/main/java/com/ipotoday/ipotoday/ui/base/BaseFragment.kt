package com.ipotoday.ipotoday.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment/*<DataBinding : ViewDataBinding>*/ : Fragment() {

//    private var _binding: DataBinding? = null
//    protected val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = getFragmentBinding(inflater, container)
//        return binding.root
//    }
//
//    abstract fun getFragmentBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ): DataBinding
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}