package com.ipotoday.ipotoday.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.utils.DATABASE_NAME

@Database(
    entities = [IPOModel::class],
    version = 1,
    exportSchema = false
)
abstract class IPOLocalDatabase : RoomDatabase() {
    abstract val ipoLocalDao: IPOLocalDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: IPOLocalDatabase? = null

        fun getInstance(context: Context): IPOLocalDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): IPOLocalDatabase {
            return Room.databaseBuilder(context, IPOLocalDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                    }
                )
                .build()
        }
    }
}