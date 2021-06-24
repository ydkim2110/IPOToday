package com.ipotoday.ipotoday.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipotoday.ipotoday.data.local.repository.HomeRepository
import com.ipotoday.ipotoday.data.model.HotIPOModel
import com.ipotoday.ipotoday.data.model.IPOTotalModel
import com.ipotoday.ipotoday.data.model.IPOModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private var _test = MutableLiveData<String>()

    val test = _test

    val homeItemList = mutableListOf(
        "Hot 종목",
        HotIPOModel(0, listOf(IPOModel(
            id = 0,
            companyName = "카카오뱅크",
            companyDescription = "은행업",
            image = "file:///android_asset/kakaobank.jpg",
            ipoPrice = "6500",
            ipoAmount = "780억원"
        ), IPOModel(
            id = 1,
            companyName = "카카오뱅크",
            companyDescription = "은행업",
            image = "file:///android_asset/kakaobank.jpg",
            ipoPrice = "6500",
            ipoAmount = "780억원"
        ), IPOModel(
            id = 2,
            companyName = "카카오뱅크",
            companyDescription = "은행업",
            image = "file:///android_asset/kakaobank.jpg",
            ipoPrice = "6500",
            ipoAmount = "780억원"
        ))),
        "전체리스트"
    )

    fun getData(index: Int) = homeItemList[index] as? IPOModel

    fun addList(list: List<*>) {
        homeItemList += list
    }

    fun addTotal(count: Int) {
        val totalItem = IPOTotalModel(count)

        homeItemList.add(totalItem)
    }

    fun selectAllIPOModelCount(result: (Int) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        result.invoke(homeRepository.selectAllIPOModelCount())
    }

    init {
        _test.postValue("Test Start!!!!!")
    }

    fun insertTestViewModel(testModel: IPOModel) =
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.insertIPOModel(testModel)
        }

}