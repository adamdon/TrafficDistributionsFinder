package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import uk.co.adamdon.trafficdistributionsfinder.ui.activities.MainActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.UiController;


public class App extends Application
{
    private UiController uiController;

    public static final String TAG = "App";

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("start up for trafficdistributionsfinder");
        uiController = new UiController(this);

    }

    public UiController getUiController()
    {
        return uiController;
    }

}