package com.reach.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.reach.todo.DATABASE_NAME
import com.reach.todo.data.daos.TaskDao
import com.reach.todo.data.entity.Task

/**
 * 2022/1/29  Reach
 */
@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance
            ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }


        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    }

}