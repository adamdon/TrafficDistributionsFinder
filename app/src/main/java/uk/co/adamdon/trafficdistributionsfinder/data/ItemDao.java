package uk.co.adamdon.trafficdistributionsfinder.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.data.models.Item;

@Dao
public interface ItemDao
{
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Insert
    void insertAll(Item... items);
}
