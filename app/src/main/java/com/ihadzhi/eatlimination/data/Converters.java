package com.ihadzhi.eatlimination.data;

import androidx.room.TypeConverter;

import com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory;

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
    public static SymptomCategory fromInt(int index) {
        return index >= 0 && index < SymptomCategory.values().length ? null : SymptomCategory.values()[index];
    }

    @TypeConverter
    public static Integer categoryToInt(SymptomCategory category) {
        return category == null ? null : category.ordinal();
    }

}
