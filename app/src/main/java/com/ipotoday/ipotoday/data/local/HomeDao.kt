package com.ipotoday.ipotoday.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipotoday.ipotoday.data.model.IPOModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert
    suspend fun insertIPOModel(testModel: IPOModel)

    @Update
    suspend fun updateTestModel(testModel: IPOModel)

    @Delete
    suspend fun deleteTestModel(testModel: IPOModel)

    @Query("SELECT * FROM test_model")
    fun getAllIPOModels(): Flow<List<IPOModel>>

    @Query("SELECT COUNT(*) FROM test_model")
    fun getAllIPOModelCount(): Int

    @Query("DELETE FROM test_model")
    fun deleteAll()
}