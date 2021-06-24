package com.ipotoday.ipotoday.data.local.repository

import com.ipotoday.ipotoday.data.local.HomeDao
import com.ipotoday.ipotoday.data.model.TestModel
import timber.log.Timber
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val testDao: HomeDao
) {

    init {
        Timber.d("DEBUG: TestRepository")
    }

    suspend fun insertTestModel(testModel: TestModel) {
        testDao.insertTestModel(testModel)
    }

}