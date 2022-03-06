package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.room.Room;

import uk.co.adamdon.trafficdistributionsfinder.data.AppDatabase;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.MainActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.UiController;


public class App extends Application
{
    private UiController uiController;
    AppDatabase appDatabase;

    public static final String TAG = "App";

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("trafficdistributionsfinder start up... (╯°□°)╯︵ ┻━┻");
        uiController = new UiController(this);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "trafficdistributionsfinder").build();
        AsyncTask.execute(() -> appDatabase.clearAllTables());
    }

    public UiController getUiController()
    {
        return uiController;
    }

    public AppDatabase getAppDatabase()
    {
        return appDatabase;
    }
}