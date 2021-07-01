package com.ipotoday.ipotoday.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ipotoday.ipotoday.data.local.IPOLocalDatabase
import com.ipotoday.ipotoday.data.local.repository.HomeRepository
import com.ipotoday.ipotoday.data.model.HotIPOModel
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.data.model.IPOTotalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel internal constructor(private val homeRepository: HomeRepository) : ViewModel() {
    val test = "test"

    val ipoList = homeRepository.selectAllIPOModels()

    val mainItemList = mutableListOf(
        "Hot 종목",
        HotIPOModel(0, listOf(
            IPOModel(
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
        )
        )),
        "전체리스트"
    )

    val homeItemList = MutableLiveData<MutableList<Any>>()

    override fun onCleared() {
        super.onCleared()
        Log.e("MainViewModel", "onCleared호출")
    }

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

    init {
        homeItemList.postValue(
            mutableListOf(
                "Hot 종목",
                HotIPOModel(0, listOf(
                    IPOModel(
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
                )
                )),
                "전체리스트"
            )
        )
    }
}

class MainViewModelFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(homeRepository) as T
    }
}

object InjectorUtils {
    private fun getHomeRepository(context: Context): HomeRepository {
        return HomeRepository.getInstance(IPOLocalDatabase.getInstance(context).ipoLocalDao)
    }

    fun provideMainViewModelFactory(
        context: Context
    ): MainViewModelFactory {
        return MainViewModelFactory(getHomeRepository(context))
    }
}