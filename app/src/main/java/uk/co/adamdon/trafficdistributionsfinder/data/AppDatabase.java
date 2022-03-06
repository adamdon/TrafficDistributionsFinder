package uk.co.adamdon.trafficdistributionsfinder.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import uk.co.adamdon.trafficdistributionsfinder.data.models.Item;

@Database(entities = {Item.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase
{
    public abstract ItemDao ItemDao();
}
