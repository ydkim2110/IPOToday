package com.ipotoday.ipotoday.data.local

import androidx.room.*
import com.ipotoday.ipotoday.data.model.IPOModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IPOLocalDao {

    @Insert
    suspend fun insertIPOModel(ipoModel: IPOModel)

    @Update
    suspend fun updateIPOModel(ipoModel: IPOModel)

    @Delete
    suspend fun deleteIPOModel(ipoModel: IPOModel)

    @Query("SELECT * FROM ipo_model")
    fun getAllIPOModels(): Flow<List<IPOModel>>

    @Query("SELECT COUNT(*) FROM ipo_model")
    fun getAllIPOModelCount(): Int

    @Query("DELETE FROM ipo_model")
    fun deleteAll()
}