package com.ipotoday.ipotoday.ui.home

import android.util.Log
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

    val ipoList = homeRepository.selectAllIPOModels()

    val mainItemList = mutableListOf(
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

    val homeItemList = MutableLiveData<MutableList<Any>>()

    fun getData(index: Int) = mainItemList[index] as? IPOModel

    fun addList(list: List<IPOModel>) {
        homeItemList.postValue(homeItemList.value?.apply { addAll(list) })
        mainItemList.addAll(list)
    }

    fun addItem(item: Any) {
        homeItemList.postValue(homeItemList.value?.apply { add(item) })
    }

    fun addTotal(count: Int) {
        val totalItem = IPOTotalModel(count)

        mainItemList.add(3, totalItem)
    }

    fun clearIPOModels() {
        mainItemList.removeAll { it is IPOModel || it is IPOTotalModel }
    }

    fun selectAllIPOModelCount(result: (Int) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        result.invoke(homeRepository.selectAllIPOModelCount())
    }

    fun insertTestViewModel(testModel: IPOModel) =
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.insertIPOModel(testModel)
        }

    fun deleteAllIPOModel() = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.deleteAllIPOModel()
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("TEST", "onCleared 호출")
    }

    init {
        _test.postValue("Test Start!!!!!")
        homeItemList.postValue(
            mutableListOf(
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
        )
    }
}