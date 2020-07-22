package com.ihadzhi.eatlimination.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static SymptomRecord.SymptomCategory fromInt(int index) {
        return index >= 0 && index < SymptomRecord.SymptomCategory.values().length ? null : SymptomRecord.SymptomCategory.values()[index];
    }

    @TypeConverter
    public static Integer categoryToInt(SymptomRecord.SymptomCategory category) {
        return category == null ? null : category.ordinal();
    }

}
