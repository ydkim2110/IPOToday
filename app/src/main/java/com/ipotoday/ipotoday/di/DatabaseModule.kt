package com.ipotoday.ipotoday.di

import android.content.Context
import androidx.room.Room
import com.ipotoday.ipotoday.data.local.HomeDao
import com.ipotoday.ipotoday.data.local.HomeDatabase
import com.ipotoday.ipotoday.data.local.repository.HomeRepository
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
    fun provideHomeRepository(homeDao: HomeDao) = HomeRepository(homeDao)

    @Singleton
    @Provides
    fun provideHomeDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, HomeDatabase::class.java, TEST_DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideHomeDao(homeDatabase: HomeDatabase) = homeDatabase.homeDao

}