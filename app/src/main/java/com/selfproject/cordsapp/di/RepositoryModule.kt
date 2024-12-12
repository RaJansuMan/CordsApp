package com.selfproject.cordsapp.di

import com.selfproject.cordsapp.data.repositoy.FilePointRepositoryImpl
import com.selfproject.cordsapp.domain.repository.FileRepository
import com.selfproject.cordsapp.domain.repository.PointRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPointRepository(
        filePointRepositoryImpl: FilePointRepositoryImpl
    ): PointRepository

    @Binds
    @Singleton
    abstract fun bindFileRepository(
        filePointRepositoryImpl: FilePointRepositoryImpl
    ): FileRepository
}