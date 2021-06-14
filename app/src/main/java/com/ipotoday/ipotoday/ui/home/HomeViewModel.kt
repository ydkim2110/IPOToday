package com.ipotoday.ipotoday.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipotoday.ipotoday.data.local.repository.TestRepository
import com.ipotoday.ipotoday.data.model.HotIPOModel
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.data.model.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val testRepository: TestRepository
) : ViewModel() {
    private var _test = MutableLiveData<String>()

    val test = _test

    val tempList = MutableLiveData<MutableList<*>>()

    init {
        _test.postValue("Test Start!!!!!")
        tempList.postValue(mutableListOf("Hot 종목", HotIPOModel(0, listOf(IPOModel(), IPOModel())), "전체리스트", IPOModel(), IPOModel()))
    }

    fun insertTestViewModel(testModel: TestModel) =
        viewModelScope.launch(Dispatchers.IO) {
            testRepository.insertTestModel(testModel)
        }

}