package com.ipotoday.ipotoday.di

import android.content.Context
import androidx.room.Room
import com.ipotoday.ipotoday.data.local.TestDao
import com.ipotoday.ipotoday.data.local.TestDatabase
import com.ipotoday.ipotoday.data.local.repository.TestRepository
import com.ipotoday.ipotoday.utils.SessionManager
import com.ipotoday.ipotoday.utils.TEST_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context)

    @Singleton
    @Provides
    fun provideTestRepository(testDao: TestDao) = TestRepository(testDao)

    @Singleton
    @Provides
    fun provideTestDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TestDatabase::class.java, TEST_DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideTestDao(testDatabase: TestDatabase) = testDatabase.testDao

}