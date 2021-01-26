package com.ihadzhi.eatlimination.data

import androidx.room.TypeConverter
import com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory
import java.util.*

object Converters {
    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @JvmStatic
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @JvmStatic
    @TypeConverter
    fun fromInt(index: Int): SymptomCategory? {
        return if (index >= 0 && index < SymptomCategory.values().size) SymptomCategory.values()[index] else null
    }

    @JvmStatic
    @TypeConverter
    fun categoryToInt(category: SymptomCategory?): Int? {
        return category?.ordinal
    }
}