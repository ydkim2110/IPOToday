package com.ipotoday.ipotoday.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ipotoday.ipotoday.data.local.repository.DetailRepository
import com.ipotoday.ipotoday.data.local.repository.HomeRepository
import com.ipotoday.ipotoday.data.model.IPOModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    val ipoModel = MutableLiveData<IPOModel>()

    fun getIPOModelById(id: Int) = repository.getIPOModelById(id)

}