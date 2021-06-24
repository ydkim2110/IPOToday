package com.ipotoday.ipotoday.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipotoday.ipotoday.data.model.IPOModel

@Dao
interface HomeDao {

    @Insert
    suspend fun insertTestModel(testModel: IPOModel)

    @Update
    suspend fun updateTestModel(testModel: IPOModel)

    @Delete
    suspend fun deleteTestModel(testModel: IPOModel)

    @Query("SELECT * FROM test_model")
    fun getAllTestModel(): LiveData<List<IPOModel>>

    @Query("DELETE FROM test_model")
    fun deleteAll()
}