package com.reach.todo.data

import androidx.room.TypeConverter
import java.util.Calendar

/**
 * 2022/1/29  Reach
 */
class Converters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

}