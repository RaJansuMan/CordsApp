package com.selfproject.cordsapp.di

import android.app.Application
import androidx.room.Room
import com.selfproject.cordsapp.data.local.AppDatabase
import com.selfproject.cordsapp.data.remote.PointApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideRemoteApi(): PointApi {
        return Retrofit.Builder()
            .baseUrl(PointApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "cords.db"
        ).build()
    }
}