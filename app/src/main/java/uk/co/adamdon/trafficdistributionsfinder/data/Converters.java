/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters
{
    @TypeConverter
    public static Date fromTimestamp(Long value)
    {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date)
    {
        return date == null ? null : date.getTime();
    }
}