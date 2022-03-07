package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Room;

import uk.co.adamdon.trafficdistributionsfinder.data.AppDatabase;
import uk.co.adamdon.trafficdistributionsfinder.ui.UiController;


public class App extends Application
{
    private UiController uiController;
    AppDatabase allItemsDatabase;
    AppDatabase filteredItemsDatabase;

    public static final String TAG = "App";

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("trafficdistributionsfinder start up... (╯°□°)╯︵ ┻━┻");
        uiController = new UiController(this);
        allItemsDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "allItemsDatabase").build();
        filteredItemsDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "filteredItemsDatabase").build();
        AsyncTask.execute(() -> allItemsDatabase.clearAllTables());
    }

    public UiController getUiController()
    {
        return uiController;
    }

    public AppDatabase getAllItemsDatabase()
    {
        return allItemsDatabase;
    }

    public AppDatabase getFilteredItemsDatabase()
    {
        return filteredItemsDatabase;
    }
}