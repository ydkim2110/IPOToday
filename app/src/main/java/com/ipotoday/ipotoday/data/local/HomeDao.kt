package com.ipotoday.ipotoday.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.data.model.TestModel

@Dao
interface HomeDao {

    @Insert
    suspend fun insertTestModel(testModel: TestModel)

    @Update
    suspend fun updateTestModel(testModel: TestModel)

    @Delete
    suspend fun deleteTestModel(testModel: TestModel)

    @Query("SELECT * FROM test_model")
    fun getAllTestModel(): LiveData<List<TestModel>>

    @Query("DELETE FROM test_model")
    fun deleteAll()
}