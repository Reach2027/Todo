package com.reach.todo.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * 2022/1/31  Reach
 */

fun calendarToString(calendar: Calendar): String {
    return SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        .format(calendar.time)
}