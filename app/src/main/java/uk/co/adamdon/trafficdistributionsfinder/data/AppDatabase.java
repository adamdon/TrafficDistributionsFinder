/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Item.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase
{
    public abstract ItemDao ItemDao();
}
