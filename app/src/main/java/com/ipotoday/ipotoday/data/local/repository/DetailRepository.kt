package com.ipotoday.ipotoday.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ipotoday.ipotoday.data.local.IPOLocalDao
import com.ipotoday.ipotoday.data.model.IPOModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val ipoLocalDao: IPOLocalDao
) {

    fun getIPOModelById(id: Int): LiveData<IPOModel> =
        ipoLocalDao.getIPOModelById(id)

}