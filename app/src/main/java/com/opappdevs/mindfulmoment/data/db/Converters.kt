package com.opappdevs.mindfulmoment.data.db

import androidx.room.TypeConverter
import java.util.*

/**
 * Converters for db fields; e.g. Date to Long
 * */
class Converters {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}