package com.ipotoday.ipotoday.data.local.repository

import com.ipotoday.ipotoday.data.local.TestDao
import com.ipotoday.ipotoday.data.model.TestModel
import timber.log.Timber
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val testDao: TestDao
) {

    init {
        Timber.d("DEBUG: TestRepository")
    }

    suspend fun insertTestModel(testModel: TestModel) {
        testDao.insertTestModel(testModel)
    }

}