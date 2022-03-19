package com.reach.todo.di

import com.reach.todo.data.localsource.TaskDataSource
import com.reach.todo.repository.DefaultTaskRepository
import com.reach.todo.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 2022/3/17  Reach
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTaskRepository(taskDataSource: TaskDataSource): TaskRepository {
        return DefaultTaskRepository(taskDataSource)
    }

}