package com.example.notesapp.util

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.Date

class DateConverter {
    @TypeConverter
    public fun timeStampFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    public fun dateFromTimeStamp(timestamp: Long) : Date? {
        return Date(timestamp)
    }
}