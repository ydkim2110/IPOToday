package com.ipotoday.ipotoday.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipotoday.ipotoday.data.model.IPOModel

@Database(
    entities = [IPOModel::class],
    version = 1,
    exportSchema = false
)
abstract class HomeDatabase : RoomDatabase() {

    abstract val homeDao: HomeDao



}