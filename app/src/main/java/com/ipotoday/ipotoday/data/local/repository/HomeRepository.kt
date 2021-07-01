package com.ipotoday.ipotoday.data.local.repository

import androidx.lifecycle.asLiveData
import com.ipotoday.ipotoday.data.local.IPOLocalDao
import com.ipotoday.ipotoday.data.model.IPOModel
import timber.log.Timber
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDao: IPOLocalDao
) {

    init {
        Timber.d("DEBUG: TestRepository")
    }

    fun selectAllIPOModelCount() = homeDao.getAllIPOModelCount()

    fun selectAllIPOModels() = homeDao.getAllIPOModels().asLiveData()

    suspend fun insertIPOModel(ipoModel: IPOModel) {
        homeDao.insertIPOModel(ipoModel)
    }

    suspend fun deleteAllIPOModel() = homeDao.deleteAll()

    companion object {
        @Volatile private var instance: HomeRepository? = null

        fun getInstance(homeDao: IPOLocalDao) =
            instance ?: synchronized(this) {
                instance ?: HomeRepository(homeDao).also { instance = it }
            }
    }
}